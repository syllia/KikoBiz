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

        public void onOfferSelected(Offer pOffre);
    }

    /**
     * Callback the activity to activate the GPS
     */

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
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);
        id = getArguments().getLong("id");
        // Inflate the layout for this fragment
        offerList = (ListView) rootView.findViewById(R.id.offer_list);
        list = new ArrayList<Offer>();
        offersListAdapter = new OffersListAdapter(this.getActivity(), list);
        offerList.setAdapter(offersListAdapter);
        loadOffers();

        offerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                offerCallback.onOfferSelected(offersListAdapter.getItem(arg2));

            }
        });
        //Picasso.with(this.getContext()).load(url)
               // .placeholder(R.drawable.any_drawable)
                //.error(R.drawable.anydrawable).into(R.id.offer_img);
        //test = (TextView)rootView.findViewById(R.id.OfferTest);
        //test.setText(String.valueOf(id));

        return rootView;
    }

    private void loadOffers(){
        String urlToLoad= Util.getFormatedAPIURL(this.getContext(), "offres/"+ id);
        HttpCustomRequest request = new HttpCustomRequest(this.getContext(),urlToLoad);
        ASyncURLRequest loadRequest = new ASyncURLRequest(){
            @Override
            protected void onPostExecute(String s){
                if(s==null){
                    Log.d("carretail", "the value returned is null");
                    return;
                }

                try {

                    Log.d("TEST SYO", s);
                    JSONObject inData = new JSONObject(s);


                    JSONArray lJsonArrayPromo = inData.getJSONArray("items");
                    //JSONArray lJsonArrayPromo = new JSONArray(s);
                    for (int i=0;i<lJsonArrayPromo.length();i++){
                        JSONObject obj = lJsonArrayPromo.getJSONObject(i);
                        Log.d("carretail", "OBJECT "+obj);
                        //String category = obj.getString("id");
                        offersListAdapter.addOffre(obj);

                    }
                    offersListAdapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("carretail", "onPostExecute json error : " + e);
                }
            }
        };

        loadRequest.execute(request);

    }
}
