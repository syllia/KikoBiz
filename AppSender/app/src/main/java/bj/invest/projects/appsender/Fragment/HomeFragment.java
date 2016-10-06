package bj.invest.projects.appsender.Fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import bj.invest.projects.appsender.Adapters.StoresAdaptes;
import bj.invest.projects.appsender.R;
import bj.invest.projects.appsender.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private StoresAdaptes mStoresAdapter;
    ArrayList<String> listOfStores;
    ListView list;
    OnShopSelectedListener shopCallBack;
    ProgressBar progressBarStores;
    TextView empty;

    public interface OnShopSelectedListener {

        public void onShopSelected(String shop);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            shopCallBack = (OnShopSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle(R.string.app_name);
        super.onCreate(savedInstanceState);
        list= (ListView)root.findViewById(R.id.list_stores);
        listOfStores = new ArrayList<String>();
        mStoresAdapter = new StoresAdaptes(getActivity(), listOfStores);
        progressBarStores = (ProgressBar)root.findViewById(R.id.progressbar_loading_stores);
        list.setAdapter(mStoresAdapter);
        empty = (TextView)root.findViewById(R.id.emptyList);
        loadStoresList();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                shopCallBack.onShopSelected(mStoresAdapter.getItem(arg2));
            }
        });


        return root;
    }
    private void loadStoresList() {
        progressBarStores.setVisibility(View.VISIBLE);
        Ion.with(this)
                .load("GET", Util.getFormatedAPIURL(getContext(), "/stores"))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        progressBarStores.setVisibility(View.GONE);
                        if(result != null) {
                            mStoresAdapter.clear();
                            for (int i = 0; i < result.size(); i++) {
                                String obj = result.get(i).getAsJsonObject().get("store").getAsString();
                                mStoresAdapter.add(obj);

                            }
                            progressBarStores.setVisibility(View.GONE);
                            if(mStoresAdapter.isEmpty()){
                                empty.setText("Aucun magasin enregistré");
                            }
                            mStoresAdapter.notifyDataSetChanged();
                        }else{

                            showAlertDialogResult("Connexion introuvable");}
                    }
                });
    }
    void showAlertDialogResult(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Problèmes de connexion");
        builder.setCancelable(false);
        builder.setMessage(s);
        builder.setNeutralButton("Recommencer",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                loadStoresList();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void onItemSelected(String position) {
        //mStoresAdapter.clear();
        CustomerList test = new CustomerList();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putString("id", position);
        test.setArguments(args);
        ft.replace(R.id.fragmentContainer, test).addToBackStack("CustomerList").commit();

    }


}
