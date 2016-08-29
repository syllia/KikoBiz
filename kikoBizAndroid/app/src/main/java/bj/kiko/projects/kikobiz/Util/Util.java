package bj.kiko.projects.kikobiz.Util;

import android.app.Activity;
import android.app.Fragment;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import bj.kiko.projects.kikobiz.Fragment.CategoriesFragment;
import bj.kiko.projects.kikobiz.Fragment.FavOffersFragment;
import bj.kiko.projects.kikobiz.Fragment.HomeFragment;
import bj.kiko.projects.kikobiz.Fragment.ParametresFragment;
import bj.kiko.projects.kikobiz.Fragment.PosterFragment;
import bj.kiko.projects.kikobiz.MainActivity;
import bj.kiko.projects.kikobiz.R;

public class Util {
	public static String getFormatedAPIURL(Context inContext,String inMethod,Map<String, String> inParams){
		String API_BASE = inContext.getResources().getString(R.string.APIBase);
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

	public static boolean verifierSiFragmentActif (MainActivity activity,String nomFragment){
		FragmentManager manager = activity.getSupportFragmentManager();
			if(manager.getBackStackEntryCount() == 0){return false;}

			if(nomFragment == manager.getBackStackEntryAt(manager.getBackStackEntryCount()-1).getName()){
				return true;
			}else{
				return false;
			}
		}

	public static String getExampleString(){
		String test = "";
		return test;
	}

	public static Bitmap byteToImage(byte[] data){
		//byte[] data = Base64.decodeBase64(dataStr);
		Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
		return bmp;

	}
	public static void lancerFragment(MainActivity activity,String nomFragment){

		if (!verifierSiFragmentActif(activity,nomFragment)){
			if (nomFragment.equals(activity.getResources().getString(R.string.FragmentCategorieName)) ){
				CategoriesFragment test = new CategoriesFragment();
				FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.fragmentContainer, test).addToBackStack(activity.getResources().getString(R.string.FragmentCategorieName)).commit();

			} else if (nomFragment.equals(activity.getResources().getString(R.string.FragmentFavOffresName))) {
				FavOffersFragment test = new FavOffersFragment();
				FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.fragmentContainer, test).addToBackStack(activity.getResources().getString(R.string.FragmentFavOffresName)).commit();

			} else if (nomFragment.equals(activity.getResources().getString(R.string.FragmentParametresName))) {
				FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
				ParametresFragment test = new ParametresFragment();
				ft.replace(R.id.fragmentContainer, test).addToBackStack(activity.getResources().getString(R.string.FragmentParametresName)).commit();

			} else if (nomFragment.equals(activity.getResources().getString(R.string.FragmentPost))) {
			FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
			PosterFragment test = new PosterFragment();
			ft.replace(R.id.fragmentContainer, test).addToBackStack(activity.getResources().getString(R.string.FragmentPost)).commit();

			} else if (nomFragment.equals(activity.getResources().getString(R.string.FragmentHomeName))) {
				FragmentManager manager = activity.getSupportFragmentManager();
				manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				FragmentTransaction ft = activity.
						getSupportFragmentManager().beginTransaction();
				HomeFragment test = new HomeFragment();
				ft.replace(R.id.fragmentContainer, test).commit();
			}

		}
	}
}


