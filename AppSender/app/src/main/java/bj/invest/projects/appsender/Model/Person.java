package bj.invest.projects.appsender.Model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sylliamehou-loko on 16-09-20.
 */
public class Person {
    private String Id;
    private String Name;
    private String PhoneNumber;
    private String ShopId;

    public Person(String id, String name, String phoneNumber, String shopId) {
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

    public Person(JSONObject obj){
        try {
            Id = obj.getString("id");
            Name = obj.getString("name");
            PhoneNumber = obj.getString("phoneNumero");
            ShopId = obj.getString("ShopId");
        }catch(JSONException e){
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

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }
}
