package bj.kiko.projects.kikobiz.Util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by sylliamehou-loko on 16-06-04.
 */
public class LocalSavedPref {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    public static final String PREFS_NAME = "UserPref";

    public LocalSavedPref(Context contxt){
        sharedPref = contxt.getSharedPreferences(PREFS_NAME, contxt.MODE_PRIVATE);
    }

    public ArrayList<String> getFavId(){
        ArrayList<String> vector = new ArrayList<>();
        String tmpStr;
        if(!sharedPref.contains("FavId")){
            return vector;
        } else{
            tmpStr = sharedPref.getString("FavId","");
            vector = parseTagStringToVector(tmpStr);
            return  vector;
        }
    }

    public void setFavId(ArrayList<String> tabId) {
        String theFullTag;
        theFullTag = parseVectorTagToString(tabId);

        editor =  sharedPref.edit();
        //editor.clear();
        editor.putString("FavId", theFullTag);
        editor.commit();
    }

    private ArrayList<String> parseTagStringToVector(String tmpStr){
        //Parsing
        String[] tokens = tmpStr.split("-");
        ArrayList<String> res = new ArrayList<>(Arrays.asList(tokens));
        return res;
    }

    private String parseVectorTagToString(ArrayList<String> tabTag){
        String res ="";
        for(int i = 0; i < tabTag.size(); i++){
            if (res.equals("")){
                res = tabTag.get(i);
            }else{
                res = res + "-" + tabTag.get(i);
            }
        }

        return res;
    }

}
