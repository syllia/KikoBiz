package bj.kiko.projects.kikobiz.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bj.kiko.projects.kikobiz.Adapters.OffersListAdapter;
import bj.kiko.projects.kikobiz.Model.Offer;
import bj.kiko.projects.kikobiz.Model.SubCategory;
import bj.kiko.projects.kikobiz.R;
import bj.kiko.projects.kikobiz.Util.ASyncURLRequest;
import bj.kiko.projects.kikobiz.Util.HttpCustomRequest;
import bj.kiko.projects.kikobiz.Util.Util;


public class OffersFragment extends Fragment {
    long id;
    TextView test;
    private ListView offerList;
    private OffersListAdapter offersListAdapter;
    private ArrayList<Offer> list;
    private OnOfferSelectedListener offerCallback;


    public interface OnOfferSelectedListener {

        public void onOfferSelected(Offer pOffre, String fragmentName);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            offerCallback = (OnOfferSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getActivity().getResources().getString(R.string.FragmentOffresName));
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);
        id = getArguments().getLong("id");
        // Inflate the layout for this fragment
        offerList = (ListView) rootView.findViewById(R.id.offer_list);
        list = new ArrayList<Offer>();
        offersListAdapter = new OffersListAdapter(this.getActivity(), list);
        offerList.setAdapter(offersListAdapter);
       // loadImages();
        loadOffers();

        offerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                offerCallback.onOfferSelected(offersListAdapter.getItem(arg2), getActivity().getResources().getString(R.string.FragmentOffresName));

            }
        });
        return rootView;
    }


    private void loadOffers(){
        Ion.with(this)
                .load(Util.getFormatedAPIURL(this.getContext(), "offresparsouscategorie/" + id))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                            @Override
                            public void onCompleted(Exception e, JsonArray result) {
                                for (int i = 0; i < result.size(); i++) {
                                    JsonObject obj = result.get(i).getAsJsonObject();
                                    offersListAdapter.addOffre(getContext(), obj);
                                    JsonArray j = obj.get("photos").getAsJsonArray();
                                    int principal=1;

                                    int o = j.get(1).getAsInt();
                                    principal =o;
                                    loadImages(principal);

                                }
                                offersListAdapter.notifyDataSetChanged();
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
                        offersListAdapter.addImage(result);
                        offersListAdapter.notifyDataSetChanged();
                    }

                });
    }
}
