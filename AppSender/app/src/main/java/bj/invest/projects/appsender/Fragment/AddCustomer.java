package bj.invest.projects.appsender.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bj.invest.projects.appsender.R;


public class AddCustomer extends Fragment {

    Button validateNewCustomer;
    public AddCustomer() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Ajouter un client");
        return inflater.inflate(R.layout.fragment_add_customer, container, false);
    }




}
