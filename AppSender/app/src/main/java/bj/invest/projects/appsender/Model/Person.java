package bj.invest.projects.appsender.Model;

import android.util.Log;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

/**
 * Created by sylliamehou-loko on 16-09-20.
 */
public class Person {
    private String Id;
    private String Name;
    private String PhoneNumber;
    private String Store;

    public Person(String id, String name, String phoneNumber, String store) {
        Id = id;
        Name = name;
        PhoneNumber = phoneNumber;
        Store = store;
    }

    public Person( String name, String phoneNumber) {
        Id = null;
        Name = name;
        PhoneNumber = phoneNumber;
        Store = null;
    }


    public Person(){}

    public Person(JsonObject inObject){
        try {
            Id = inObject.get("id").getAsString();
            Name = inObject.get("name").getAsString();
            PhoneNumber = inObject.get("number").getAsString();
            Store = inObject.get("store").getAsString();
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

    public String getStore() {
        return Store;
    }

    public void setStore(String store) {
        Store = store;
    }
}
