package bj.kiko.projects.kikobiz.Model;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sylliamehou-loko on 16-08-28.
 */
public class Picture {
    private int idImageOffer;
    private int idOffer;
    private String name;

    public Picture(JSONObject inObject) {
        try {
            this.idImageOffer = inObject.getInt("ww");
            this.idOffer = inObject.getInt("");
            this.name = inObject.getString("byteArray");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}