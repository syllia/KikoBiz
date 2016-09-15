package bj.kiko.projects.kikobiz.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import bj.kiko.projects.kikobiz.Adapters.RecyclerViewDataAdapter;
import bj.kiko.projects.kikobiz.Adapters.SectionListDataAdapter;
import bj.kiko.projects.kikobiz.Model.SectionDataModel;
import bj.kiko.projects.kikobiz.Model.SingleItemModel;
import bj.kiko.projects.kikobiz.R;
import bj.kiko.projects.kikobiz.Util.Util;


public class HomeFragment extends Fragment {

    ArrayList<SectionDataModel> allSampleData = new ArrayList<>();
    SectionListDataAdapter list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle(getActivity().getResources().getString(R.string.FragmentHomeName));
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        createDummyData();


        RecyclerView my_recycler_view = (RecyclerView)view.findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this.getActivity(), allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);

        return view;


    }

    private void loadOffers(){
        Ion.with(this)
                .load(Util.getFormatedAPIURL(this.getContext(), "offres/"))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        for (int i = 0; i < result.size(); i++) {
                            JsonObject obj = result.get(i).getAsJsonObject();
                            JsonArray j = obj.get("photos").getAsJsonArray();
                            int principal=1;
                            int o = j.get(0).getAsInt();
                            loadImages(principal);

                        }

                    }
                });
    }

    private void loadImages(int photoId){
        Ion.with(this)
                .load(Util.getFormatedAPIURL(this.getContext(), "imagesparoffre/"+ photoId))
                .asByteArray()
                .setCallback(new FutureCallback<byte[]>() {
                    @Override
                    public void onCompleted(Exception e, byte[] result) {
                        if(result!=null){
                        list.addImage(result);
                        list.notifyDataSetChanged();
                        }
                    }

                });
    }

    public void createDummyData() {
        for (int i = 1; i <= 2; i++) {

            SectionDataModel dm = new SectionDataModel();

            dm.setHeaderTitle("Section " + i);

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();

            for (int j = 0; j <= 3; j++) {
                singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
            }
            list = new SectionListDataAdapter(getContext(),singleItem);
            loadOffers();

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }


}
