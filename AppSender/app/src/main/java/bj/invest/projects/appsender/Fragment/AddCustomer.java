package bj.invest.projects.appsender.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import bj.invest.projects.appsender.R;
import bj.invest.projects.appsender.Util;


public class AddCustomer extends Fragment {

    Button validateNewCustomer;
    EditText name;
    EditText store;
    EditText number;



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

        View root =  inflater.inflate(R.layout.fragment_add_customer, container, false);

        name = (EditText)root.findViewById(R.id.nameEditText);
        store = (EditText)root.findViewById(R.id.storeEditText);
        number = (EditText)root.findViewById(R.id.numberEditText);
        validateNewCustomer = (Button)root.findViewById(R.id.addCustomerButton);
        validateNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    JsonObject obj = new JsonObject();
                    obj.addProperty("name", name.getText().toString());
                    obj.addProperty("number", number.getText().toString());
                    obj.addProperty("store", store.getText().toString());
                    postCustomer(obj);
                    getActivity().onBackPressed();


            }
        });
        return root;
    }

    public void postCustomer(JsonObject obj){
        Ion.with(this)
                .load("POST", Util.getFormatedAPIURL(this.getContext(), "customers/"))
                .setJsonObjectBody(obj)
                .asJsonObject();

    }

}
