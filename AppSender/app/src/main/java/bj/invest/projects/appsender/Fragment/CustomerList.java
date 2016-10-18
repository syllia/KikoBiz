package bj.invest.projects.appsender.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import bj.invest.projects.appsender.Adapters.SimpleListAdapter;
import bj.invest.projects.appsender.Model.Customer;
import bj.invest.projects.appsender.R;
import bj.invest.projects.appsender.Util;


public class CustomerList extends Fragment {
    ArrayList<Customer> dummyList = new ArrayList<>();
    ArrayList<Customer> ListToLoad = new ArrayList<>();
    ListView ListOfCustomers;
    SimpleListAdapter CustomerAdapter;
    String store;
    SearchView searchField;
    ProgressBar progressBarCustomer;
    TextView empty;
    OnShopListener shopCallback;



    public interface OnShopListener {

        public void onAddUser(String shop);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            shopCallback = (OnShopListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_customer_list, container, false);
        store =getArguments().getString("id");

        getActivity().setTitle(store);
        progressBarCustomer = (ProgressBar)root.findViewById(R.id.progressbar_loading_customers);
        loadList();
        empty = (TextView)root.findViewById(R.id.emptyCustomerList);


        setHasOptionsMenu(true);
        ListOfCustomers = (ListView) root.findViewById(R.id.customer_list);
        CustomerAdapter = new SimpleListAdapter(this.getActivity(), ListToLoad, new SimpleListAdapter.ListAdapterListener() {
            @Override
            public void onClickAtButton(int position, int type) {
                if(type == 0){
                    Customer itemToUpdate = dummyList.get(position);
                    showAlertDialogUpdate(itemToUpdate);
                }
                else{
                    Customer itemToRemove = dummyList.get(position);
                   showAlertDialogDelete(itemToRemove);

                }


            }
        });
        ListOfCustomers.setAdapter(CustomerAdapter);
        View childView = getActivity().getLayoutInflater().inflate(R.layout.customer_cell, null);

        searchField = (SearchView) root.findViewById(R.id.searchViewCustomer);
        searchField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchField.setIconified(false);
                //searchField.setCol
            }
        });
        searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("on query: ", newText);
                filter(dummyList, newText);
                return true;
            }

        });


        ListOfCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return root;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_user:
                AddCustomer test = new AddCustomer();
               shopCallback.onAddUser(store);

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void filter(List<Customer> users, String query) {
        query = query.toLowerCase();
        CustomerAdapter.clear();
        //CustomerAdapter.notifyDataSetChanged();
        for (Customer u : users) {
            final String text = u.getName().toLowerCase();
            final String text2 = u.getPhoneNumber();
            if (text.contains(query) || text2.contains(query)) {
                CustomerAdapter.add(u);

            }
            CustomerAdapter.notifyDataSetChanged();
        }
    }

    private void loadList() {
        progressBarCustomer.setVisibility(View.VISIBLE);
        Ion.with(this)
                .load("GET", Util.getFormatedAPIURL(this.getContext(), "/customers/bystores/"+ store.replace(" ","%20")))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        progressBarCustomer.setVisibility(View.GONE);

                       if(result != null) {
                           CustomerAdapter.clear();
                           dummyList.clear();
                           for (int i = 0; i < result.size(); i++) {
                               JsonObject obj = result.get(i).getAsJsonObject();
                               CustomerAdapter.add(obj);
                               dummyList.add(new Customer(obj));
                           }

                           CustomerAdapter.notifyDataSetChanged();
                           if (CustomerAdapter.isEmpty()){
                               empty.setText("Aucun client enregistré pour ce magasin");
                           }
                       }
                       else{

                           showAlertDialogResult("Connexion introuvable");}
                    }
                });

    }
    void showAlertDialogResult(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Problèmes de connexion");
        builder.setCancelable(false);
        builder.setMessage(s);
        builder.setPositiveButton("Recommencer",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                loadList();
            }
        });
        builder.setNegativeButton("Annuler",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               getActivity().onBackPressed();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void deleteCustomer(String id){
        Ion.with(this)
                .load("DELETE", Util.getFormatedAPIURL(this.getContext(), "customers/"+id))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        CustomerAdapter.clear();
                        dummyList.clear();
                        loadList();
                    }
                });
    }

    void showAlertDialogDelete(final Customer itemToRemove){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Supprimer client");

        builder.setMessage("Veuillez confirmer la suppresion du client: \n"+itemToRemove.getName()+"\n"+itemToRemove.getPhoneNumber());

        builder.setPositiveButton("Supprimer",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                deleteCustomer(itemToRemove.getId());
                dummyList.remove(itemToRemove);
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();

    }

    void showAlertDialogUpdate(final Customer itemToUpdate){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Vente client");

        builder.setMessage("Veuillez confirmer l'achat du client: \n"+itemToUpdate.getName()+"\n"+itemToUpdate.getPhoneNumber());

        builder.setPositiveButton("Mettre à jour",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                updateBuyDate(itemToUpdate.getId());
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();

    }

    private void updateBuyDate(String id){
        Ion.with(this)
                .load("PUT", Util.getFormatedAPIURL(this.getContext(), "customers/"+id))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        CustomerAdapter.clear();
                        dummyList.clear();
                        loadList();
                    }
                });
    }



}
