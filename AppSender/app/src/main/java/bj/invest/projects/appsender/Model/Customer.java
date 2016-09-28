package bj.invest.projects.appsender.Model;

import android.content.Context;

import com.google.gson.JsonObject;

/**
 * Created by sylliamehou-loko on 16-09-20.
 */
public class Customer extends Person{
    public Customer(String name, String num){
        super(name, num);
    }
    public Customer(JsonObject inObject){
        super();
    }
}
