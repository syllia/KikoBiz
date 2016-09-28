package bj.invest.projects.appsender.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import bj.invest.projects.appsender.Adapters.SimpleListAdapter;
import bj.invest.projects.appsender.Model.Customer;
import bj.invest.projects.appsender.Model.Person;
import bj.invest.projects.appsender.R;
import bj.invest.projects.appsender.Util;


public class CustomerList extends Fragment {
    ArrayList<Customer> dummyList = new ArrayList<>();
    ArrayList<Customer> ListToLoad = new ArrayList<>();
    ListView ListOfCustomers;
    SimpleListAdapter CustomerAdapter;
    SearchView searchField;

    public CustomerList() {
        // Required empty public constructor
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
        getActivity().setTitle("Liste des clients");
        loadList();
        if (dummyList.isEmpty()) {
        //    GenerateDummyData();
        }

        setHasOptionsMenu(true);
        ListOfCustomers = (ListView) root.findViewById(R.id.customer_list);
        CustomerAdapter = new SimpleListAdapter(this.getActivity(), ListToLoad);
        ListOfCustomers.setAdapter(CustomerAdapter);
        /*delete = (Button)root.findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer itemToRemove = (Customer) v.getTag();
                CustomerAdapter.remove(itemToRemove);
                dummyList.remove(itemToRemove);
            }
        });*/
        searchField = (SearchView) root.findViewById(R.id.searchViewCustomer);

        searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("on query: ", newText);
                GenerateDummyData();
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

    public void removeCustomer(View v) {
        Customer itemToRemove = (Customer) v.getTag();
        CustomerAdapter.remove(itemToRemove);
        dummyList.remove(itemToRemove);
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
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, test).addToBackStack("list").commit();
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
        Ion.with(this)
                .load("GET", Util.getFormatedAPIURL(this.getContext(), "customers/"))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        Log.d("onCompleted: ", result.toString());
                        for (int i = 0; i < result.size(); i++) {
                            JsonObject obj = result.get(i).getAsJsonObject();
                            CustomerAdapter.add(obj);
                        }
                        CustomerAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void deleteCustomer(String id){
        Ion.with(this)
                .load("DELETE", Util.getFormatedAPIURL(this.getContext(), "customers/"+id))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        loadList();
                    }
                });
    }

    private void GenerateDummyData() {
        for (Customer customer : CustomerAdapter.getList()) {
            dummyList.add(customer);
        }

    }


}
