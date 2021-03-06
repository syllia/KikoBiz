package bj.kiko.projects.kikobiz.Model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import bj.kiko.projects.kikobiz.R;

/**
 * Created by sylliamehou-loko on 16-05-14.
 */

public class Offer implements Parcelable{

    public static final int NB_HOURS_BEFORE_PRIORITY_CHECK = 24;
    private int offerId;
    private long cost;
    private String startDate;
    private String endDate;
    private Country country;
    private String name;
    private int nbViews;
    private List<Integer>pictures;
    private String description;
    private int subCategoryId;
    private int userId;
    private Priority priority;


    public Offer(String startDate, String endDate, String name, int nbViews, List<Integer> pictures, String description, int subCategoryId, int userId, Priority priority) {
        this.startDate = startDate;
        this.endDate = endDate;
        //this.country = country;
        this.name = name;
        this.nbViews = nbViews;
        this.pictures = pictures;
        this.description = description;
        this.subCategoryId = subCategoryId;
        this.userId = userId;
        this.priority = priority;
    }

    public Offer(){

    }

    //http://www.infos-mobiles.com/wp-content/uploads/2016/04/apple.png
    public Offer(Context ctx, JsonObject inObject){
        if(inObject != null){
            try {
                this.offerId = inObject.get("offerId").getAsInt();
                this.cost = inObject.get("cost").getAsLong();
                //this.startDate = inObject.getString("startDate");
                //this.endDate = inObject.getString("fin");
                //this.country = inObject.getString("mEntreprise");
                //inObject.get(ctx.getString(R.string.offerImages));
                this.name = inObject.get("name").getAsString();
                this.nbViews = inObject.get("nbViews").getAsInt();

                //this.pictures = inObject.getString(ctx.getString(R.string.offerImages));
                JsonArray j = inObject.get("photos").getAsJsonArray();
                this.pictures=new ArrayList<>();
                for (int i = 0; i < j.size(); i++) {
                    int a= j.get(i).getAsInt();

                    this.pictures.add(a);
                }
                this.description = inObject.get("description").getAsString();
                this.userId = inObject.get("userId").getAsInt();
                this.subCategoryId = inObject.get("idSousCategorie").getAsInt();


            } catch (JsonIOException e) {
                e.printStackTrace();
            }

        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getOfferId() {
        return offerId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbViews() {
        return nbViews;
    }

    public void setNbViews(int nbViews) {
        this.nbViews = nbViews;
    }

    public List<Integer> getPictures() {
        return pictures;
    }

    public void setPictures(List<Integer> pictures) {
        this.pictures = pictures;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setEffectivePriority(){
        Date currentDate = new Date();
        long milliseconds = currentDate.getTime() - this.toDate(this.getStartDate()).getTime();
        int hours   = (int) ((milliseconds / (1000*60*60)));
        if(hours >= NB_HOURS_BEFORE_PRIORITY_CHECK){
            this.priority.setPriorityToCompare(this.priority.getRealPriority());
        }
    }

    private Date toDate(String date){
        Date convertedDate = new Date();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            convertedDate = sdf.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return convertedDate;
    }

    public Offer(Parcel source) {

        if (source.dataSize() > 0) {
            this.offerId = source.readInt();
            this.cost = source.readLong();
            this.startDate = source.readString();
            this.endDate = source.readString();
            this.name = source.readString();
            this.nbViews = source.readInt();
            this.priority = source.readParcelable(Offer.class.getClassLoader());;
            this.description = source.readString();
            this.userId = source.readInt();
            this.subCategoryId = source.readInt();

        }

    }


    public final static Parcelable.Creator<Offer> CREATOR = new Parcelable.Creator<Offer>() {
        public Offer createFromParcel(Parcel in) {
            return new Offer(in);
        }

        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.offerId);
        dest.writeDouble(this.cost);
        dest.writeString(this.startDate);
        dest.writeString(this.endDate);
        dest.writeString(this.name);
        dest.writeInt(this.nbViews);
        dest.writeParcelable(this.priority, flags);
        dest.writeString(this.description);
        dest.writeInt(this.userId);
        dest.writeInt(this.subCategoryId);

    }

}
