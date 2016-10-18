package bj.invest.projects.appsender.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import bj.invest.projects.appsender.R;
import bj.invest.projects.appsender.Util;


public class AddCustomer extends Fragment {

    Button validateNewCustomer;
    EditText name;
    EditText number;
    ArrayAdapter storeSpinnerAdapter;
    List<String> listOfStores= new ArrayList<>();
    ProgressBar progressBarAddCustomer;
    String store;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Ajouter un client");
        store = getArguments().getString("shop");
        View root =  inflater.inflate(R.layout.fragment_add_customer, container, false);
        progressBarAddCustomer = (ProgressBar)root.findViewById(R.id.progressbar_add_customer);
        name = (EditText)root.findViewById(R.id.nameEditText);
        //store = (EditText)root.findViewById(R.id.storeEditText);
        number = (EditText)root.findViewById(R.id.numberEditText);


        loadStoresList();


        validateNewCustomer = (Button)root.findViewById(R.id.addCustomerButton);
        validateNewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() &&!number.getText().toString().isEmpty()){
                    JsonObject obj = new JsonObject();
                    obj.addProperty("name", name.getText().toString());
                    obj.addProperty("number", number.getText().toString());
                    obj.addProperty("store", store);
                    showAlertDialogConfirmation(obj);
                }else {
showAlertDialogInformationManquantes();
                }

            }
        });
        return root;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void loadStoresList() {
       progressBarAddCustomer.setVisibility(View.VISIBLE);
        Ion.with(this)
                .load("GET", Util.getFormatedAPIURL(getContext(), "/stores"))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        progressBarAddCustomer.setVisibility(View.GONE);
                        if(result != null) {

                            for (int i = 0; i < result.size(); i++) {
                                String obj = result.get(i).getAsJsonObject().get("store").getAsString();
                                listOfStores.add(obj);

                            }
                            storeSpinnerAdapter.notifyDataSetChanged();
                        }else{

                            showAlertDialogResult("Connexion introuvable");}
                    }
                });
    }

    public void postCustomer(JsonObject obj){
        Ion.with(this)
                .load("POST", Util.getFormatedAPIURL(this.getContext(), "customers/"))
                .setJsonObjectBody(obj)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(result != null) {
                            JsonElement id = result.get("id");
                            if (id.isJsonNull()){
                                showAlertDialogFail("Ce numero de client existe déjà");
                            }else{
                            showAlertDialogResult("Nouveau client enregisté");
                            getActivity().onBackPressed();
                            }
                        }else{

                        showAlertDialogFail("Connexion introuvable");}

                    }
                });

    }
    void showAlertDialogConfirmation(final JsonObject obj){


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Enregistrement du client");
        builder.setCancelable(false);
        builder.setMessage("Voulez-vous enregistrer le client:\n"+ name.getText().toString() + "\n"+ number.getText().toString()+ "?");

        builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                postCustomer(obj);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    void showAlertDialogResult(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Enregistrement du client");
        builder.setCancelable(false);
        builder.setMessage(s);
        builder.setNeutralButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    void showAlertDialogFail(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Échec de l'enregistrement");
        builder.setCancelable(false);
        builder.setMessage(s);
        builder.setNeutralButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    void showAlertDialogInformationManquantes(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Enregistrement du client");
        builder.setCancelable(false);
        builder.setMessage("Informations manquantes pour le client");

        builder.setPositiveButton("Revenir",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();

    }





}
