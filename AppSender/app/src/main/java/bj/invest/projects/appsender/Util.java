package bj.invest.projects.appsender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;

import java.util.Map;

/**
 * Created by julioadossou on 2016-09-27.
 */
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

    public static boolean verifierSiFragmentActif (StoreActivity activity,String nomFragment){
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
}
