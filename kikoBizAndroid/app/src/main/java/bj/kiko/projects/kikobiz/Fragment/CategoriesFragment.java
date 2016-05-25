package bj.kiko.projects.kikobiz.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import bj.kiko.projects.kikobiz.Adapters.ExpandListAdapter;
import bj.kiko.projects.kikobiz.Model.Category;
import bj.kiko.projects.kikobiz.R;
import bj.kiko.projects.kikobiz.Util.ASyncURLRequest;
import bj.kiko.projects.kikobiz.Util.HttpCustomRequest;
import bj.kiko.projects.kikobiz.Util.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    private ExpandListAdapter ExpAdapter;
    private ExpandableListView ExpandList;
    private ArrayList<Category> list;
    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_categories, container, false);
        ExpandList = (ExpandableListView) view.findViewById(R.id.exp_list);
        list = new ArrayList<Category>();
        ExpAdapter = new ExpandListAdapter(this.getContext(), list);
        ExpandList.setAdapter(ExpAdapter);
        loadCategories();
        return  view;
    }

    private void loadCategories(){
        String urlToLoad= Util.getFormatedAPIURL(this.getContext(), "categories/");
        HttpCustomRequest request = new HttpCustomRequest(this.getContext(),urlToLoad);
        ASyncURLRequest loadRequest = new ASyncURLRequest(){
            @Override
            protected void onPostExecute(String s){
                if(s==null){
                    Log.d("carretail", "the value returned is null");
                    return;
                }

                try {


                    JSONObject inData = new JSONObject(s);


                    JSONArray lJsonArrayPromo = inData.getJSONArray("items");
                    //JSONArray lJsonArrayPromo = new JSONArray(s);
                    for (int i=0;i<lJsonArrayPromo.length();i++){
                        JSONObject obj = lJsonArrayPromo.getJSONObject(i);
                        Log.d("carretail", "OBJECT "+obj);
                        String category = obj.getString("id");
                        ArrayList<String> res = loadSubCategories(category);

                        ExpAdapter.add(category, res);
                    }
                    ExpAdapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("carretail", "onPostExecute json error : " + e);
                }
            }
        };

        loadRequest.execute(request);

    }

    private ArrayList<String> loadSubCategories(String category){
        String query = category;
        try {
             query = URLEncoder.encode(category, "utf-8");
        }catch (java.io.UnsupportedEncodingException e){
            e.printStackTrace();
        }

        String urlToLoad= Util.getFormatedAPIURL(this.getContext(), "souscategories/"+query);
        final ArrayList<String> result = new ArrayList<String>();
        HttpCustomRequest request = new HttpCustomRequest(this.getContext(),urlToLoad);
        ASyncURLRequest loadRequest = new ASyncURLRequest(){
            @Override
            protected void onPostExecute(String s){
                if(s==null){
                    Log.d("carretail", "the value returned is null");
                    return;
                }

                try {


                    JSONObject inData = new JSONObject(s);


                    JSONArray lJsonArrayPromo = inData.getJSONArray("items");
                    //JSONArray lJsonArrayPromo = new JSONArray(s);
                    for (int i=0;i<lJsonArrayPromo.length();i++){
                        JSONObject obj = lJsonArrayPromo.getJSONObject(i);
                        Log.d("carretail", "OBJECT "+obj);
                        String category = obj.getString("mName");
                        result.add(category);
                    }
                    ExpAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("carretail", "onPostExecute json error : " + e);
                }
            }
        };
        loadRequest.execute(request);
        return result;

    }


}
