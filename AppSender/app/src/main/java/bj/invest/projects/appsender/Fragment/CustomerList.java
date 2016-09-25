package bj.invest.projects.appsender.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.List;

import bj.invest.projects.appsender.Adapters.SimpleListAdapter;
import bj.invest.projects.appsender.Model.Customer;
import bj.invest.projects.appsender.Model.Person;
import bj.invest.projects.appsender.R;


public class CustomerList extends Fragment {
    ArrayList<Person> dummyList = new ArrayList<>();
    ArrayList<Person> ListToLoad = new ArrayList<>();
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
        View root =  inflater.inflate(R.layout.fragment_customer_list, container, false);
        getActivity().setTitle("Liste des clients");
        if (dummyList.isEmpty()){
            GenerateDummyData();
        }

        setHasOptionsMenu(true);
        ListOfCustomers = (ListView)root.findViewById(R.id.customer_list);
        CustomerAdapter =new SimpleListAdapter(this.getActivity(), ListToLoad);
        ListOfCustomers.setAdapter(CustomerAdapter);


        searchField = (SearchView)root.findViewById(R.id.searchViewCustomer);

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
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, test).addToBackStack("list").commit();
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void filter(List<Person> users, String query) {
        query = query.toLowerCase();
        ListToLoad.clear();
        //CustomerAdapter.notifyDataSetChanged();
        for (Person u : users) {
            final String text = u.getName().toLowerCase();
            final String text2 = u.getPhoneNumber();
            if (text.contains(query) || text2.contains(query)) {
                ListToLoad.add(u);

            }
            CustomerAdapter.notifyDataSetChanged();
        }
    }

    private void GenerateDummyData(){
        dummyList.add(new Customer("Mathieu Kerekou", "2345678"));
        dummyList.add(new Customer("Martin Luther King", "90970700"));
        dummyList.add(new Customer("Michel Adovelandé", "00889098"));
        dummyList.add(new Customer("Abdoulaye Bio Tchané", "89878687"));
        dummyList.add(new Customer("Andrianjatovo Miary", "879770808"));
        ListToLoad.add(new Customer("Mathieu Kerekou", "2345678"));
        ListToLoad.add(new Customer("Martin Luther King", "90970700"));
        ListToLoad.add(new Customer("Michel Adovelandé", "00889098"));
        ListToLoad.add(new Customer("Abdoulaye Bio Tchané", "89878687"));
        ListToLoad.add(new Customer("Andrianjatovo Miary", "879770808"));
    }


}
