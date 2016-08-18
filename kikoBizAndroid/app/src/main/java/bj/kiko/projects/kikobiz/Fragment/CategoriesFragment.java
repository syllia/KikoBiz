package bj.kiko.projects.kikobiz.Fragment;


import android.content.Context;
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
import java.util.HashMap;

import bj.kiko.projects.kikobiz.Adapters.ExpandListAdapter;
import bj.kiko.projects.kikobiz.Model.Category;
import bj.kiko.projects.kikobiz.Model.SubCategory;
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
    OnSubCategorySelectedListener mCallback;
    public CategoriesFragment() {
        // Required empty public constructor
    }

    //interface pour envoyer un message à l'activité en cas de click dans la list (interface implementee dans l'activité)
    //callBack dans le onCreate
    public interface OnSubCategorySelectedListener {
        public void onItemSelected(long position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnSubCategorySelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(getActivity().getResources().getString(R.string.FragmentCategorieName));
        View view =  inflater.inflate(R.layout.fragment_categories, container, false);
        ExpandList = (ExpandableListView) view.findViewById(R.id.exp_list);
        list = new ArrayList<Category>();
        ExpAdapter = new ExpandListAdapter(this.getContext(), list);
        ExpandList.setAdapter(ExpAdapter);
        loadCategories();
        ExpandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mCallback.onItemSelected(ExpAdapter.getChildId(groupPosition, childPosition));
                return false;
            }
        });
        return  view;
    }

    private void loadCategories(){
        //Load les categories et les sous categories pour la liste deroulante
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
                        ArrayList<SubCategory> res = loadSubCategories(category);

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

    private ArrayList<SubCategory> loadSubCategories(String category){
        String query = category;
        try {
             query = URLEncoder.encode(category, "utf-8");
        }catch (java.io.UnsupportedEncodingException e){
            e.printStackTrace();
        }

        String urlToLoad= Util.getFormatedAPIURL(this.getContext(), "souscategories/"+query);
        final ArrayList<SubCategory> result = new ArrayList<SubCategory>();
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
                        Long id = obj.getLong("id");
                        result.add(new SubCategory(id, category));
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
