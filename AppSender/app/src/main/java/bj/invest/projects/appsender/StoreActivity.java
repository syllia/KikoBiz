package bj.invest.projects.appsender;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import java.util.ArrayList;

import bj.invest.projects.appsender.Adapters.StoresAdaptes;
import bj.invest.projects.appsender.Fragment.CustomerList;
import bj.invest.projects.appsender.Fragment.HomeFragment;
import bj.invest.projects.appsender.Model.Customer;

public class StoreActivity extends AppCompatActivity implements HomeFragment.OnShopSelectedListener{
    private StoresAdaptes mStoresAdapter;
    ArrayList<String> listOfStores;
    TextView emptyText;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        FragmentManager manager = this.getSupportFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = this.
                getSupportFragmentManager().beginTransaction();
        HomeFragment test = new HomeFragment();
        ft.replace(R.id.fragmentContainer, test).commit();

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void onShopSelected(String position) {
        //mStoresAdapter.clear();
        CustomerList test = new CustomerList();
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putString("id", position);
        test.setArguments(args);
        ft.replace(R.id.fragmentContainer, test).addToBackStack("CustomerList").commit();

    }


}
