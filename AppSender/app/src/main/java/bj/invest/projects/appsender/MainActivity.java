package bj.invest.projects.appsender;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import bj.invest.projects.appsender.Fragment.AddCustomer;
import bj.invest.projects.appsender.Fragment.CustomerList;
import bj.invest.projects.appsender.Fragment.HomeFragment;
import bj.invest.projects.appsender.R;

public class MainActivity extends AppCompatActivity implements CustomerList.OnShopListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent intent = getIntent();
        String position = intent.getStringExtra("id");
        CustomerList test = new CustomerList();
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putString("id", position);
        test.setArguments(args);
        ft.replace(R.id.secondFragmentContainer, test).addToBackStack("CustomerList").commit();

    }

    public void onAddUser(String shop) {
        //mStoresAdapter.clear();
        AddCustomer test = new AddCustomer();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putString("Shop", shop);
        test.setArguments(args);
        ft.replace(R.id.fragmentContainer, test).addToBackStack("AddUser").commit();

    }



}
