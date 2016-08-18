package bj.kiko.projects.kikobiz.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bj.kiko.projects.kikobiz.Adapters.OffersListAdapter;
import bj.kiko.projects.kikobiz.Model.Offer;
import bj.kiko.projects.kikobiz.R;
import bj.kiko.projects.kikobiz.Util.ASyncURLRequest;
import bj.kiko.projects.kikobiz.Util.HttpCustomRequest;
import bj.kiko.projects.kikobiz.Util.LocalSavedPref;
import bj.kiko.projects.kikobiz.Util.Util;


public class FavOffersFragment extends Fragment {
    private OffersListAdapter mOffreAdapter;
    private ListView mListView;
    private OnOfferFavSelectedListener offerFavCallback;
    private TextView emptyText;

    public interface OnOfferFavSelectedListener {

        public void onFavSelected(Offer pOffre);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            offerFavCallback = (OnOfferFavSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(getActivity().getResources().getString(R.string.FragmentFavOffresName));
        View rootView = inflater.inflate(R.layout.fragment_fav_offers, container, false);
        mOffreAdapter = new OffersListAdapter(this.getActivity(), new ArrayList<Offer>());
        mListView = (ListView) rootView.findViewById(R.id.offer_list_fav);
        //emptyText = (TextView) rootView.findViewById(R.id.emptyListeMesoffres);

        mListView.setAdapter(mOffreAdapter);
        LocalSavedPref lc = new LocalSavedPref(getContext());
        List<String> secteurList = lc.getFavId();

        for (int i = 0; i < secteurList.size(); ++i) { //Depilement de tout les fragments
            Log.d("TEST LIST FAV: ", secteurList.get(i));
            loadOffreList(secteurList.get(i));
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                offerFavCallback.onFavSelected(mOffreAdapter.getItem(arg2));

            }
        });

        return rootView;
    }

    private void loadOffreList(String id) {
        String urlToLoad = Util.getFormatedAPIURL(this.getContext(), "offres/id/" + id);
        HttpCustomRequest request = new HttpCustomRequest(this.getContext(), urlToLoad);
        ASyncURLRequest loadRequest = new ASyncURLRequest() {
            @Override
            protected void onPostExecute(String s) {

                if (s == null) {
                    //emptyText.setText("Favoris vides");
                    mListView.setEmptyView(emptyText);
                    Log.d("carretail", "the value returned is null");
                    return;
                }
                Log.d("carretail", "OBJECT ");
                try {
                    JSONObject inData = new JSONObject(s);
                    mOffreAdapter.addOffre(inData);
                    mOffreAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("carretail", "onPostExecute json error : " + e);
                }
            }
        };
        loadRequest.execute(request);
    }


}
