package bj.kiko.projects.kikobiz.Util;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Util {
	public static String getFormatedAPIURL(Context inContext,String inMethod,Map<String, String> inParams){
		String API_BASE = "http://10.0.2.2:8888/_ah/api/monapi/v1/";
		//String API_format = inContext.getResources().getString(R.string.API_FORMAT);
		if(inParams==null){
			return API_BASE+inMethod;
		}else{
			return API_BASE+inMethod;
		}
	}
	public static String getFormatedAPIURL(Context inContext,String inMethod){
		return Util.getFormatedAPIURL(inContext, inMethod,null);
	}

}
