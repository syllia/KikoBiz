package bj.invest.projects.appsender.Model;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sylliamehou-loko on 16-09-20.
 */
public class Person {
    private String Id;
    private String Name;
    private String PhoneNumber;
    private Integer ShopId;

    public Person(String id, String name, String phoneNumber, Integer shopId) {
        Id = id;
        Name = name;
        PhoneNumber = phoneNumber;
        ShopId = shopId;
    }

    public Person( String name, String phoneNumber) {
        Id = null;
        Name = name;
        PhoneNumber = phoneNumber;
        ShopId = null;
    }


    public Person(){}

    public Person(JsonObject inObject){
        try {
            Id = inObject.get("id").getAsString();
            Name = inObject.get("name").getAsString();
            PhoneNumber = inObject.get("number").getAsString();
            ShopId = inObject.get("ShopId").getAsInt();
        }catch(JsonIOException e){
            Log.d("Person JSON Constructor", "JSONException raised ");
        }

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Integer getShopId() {
        return ShopId;
    }

    public void setShopId(Integer shopId) {
        ShopId = shopId;
    }
}
