package bj.kiko.projects.kikobiz.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

import bj.kiko.projects.kikobiz.Model.Offer;
import bj.kiko.projects.kikobiz.R;
import bj.kiko.projects.kikobiz.Util.Util;

/**
 * Created by sylliamehou-loko on 16-05-30.
 */
public class OffersListAdapter extends ArrayAdapter<Offer> {

private ArrayList<Offer> mOffreList;
private Activity mActivity;
private Bitmap bmp;
    public OffersListAdapter(Activity inActivity, ArrayList<Offer> inList){
        super(inActivity, R.layout.offer_cell, inList);
        this.mActivity = inActivity;
        mOffreList =inList;

        //mImageList

        }
    public void addImage(JSONObject obj){
        try {
             //data = (obj.get("byteArray"));
            //String encoded = obj.getString("byteArray");
            String test = Util.getExampleString();
            byte[] decoded = Base64.decode(obj.getString("byteArray"), Base64.DEFAULT);
            this.bmp = Util.byteToImage(decoded);
            Log.d("carretail Image ", "OBJECT " + decoded);
        }
     catch (JSONException e) {
        e.printStackTrace();
        Log.d("carretail", "onPostExecute json error : " + e);
    }

    }

public void addOffre(Context ctx, JSONObject inJson){
        Offer outItem = new Offer(ctx, inJson);
        mOffreList.add(outItem);
        }

public ArrayList<Offer> getOffreList(){
        return mOffreList;
        }

/*public void setListeOffre(ArrayList<Offer> listeTrie){
        mOffreList = listeTrie;
        this.notifyDataSetChanged();
        }*/

@Override
public int getCount() {
        // TODO Auto-generated method stub
        return mOffreList.size();
        }

@Override
public Offer getItem(int arg0) {
        // TODO Auto-generated method stub
        return mOffreList.get(arg0);
        }

@Override
public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
        }

static class ViewHolder {
    public TextView title;
    public ImageView img;
    public TextView cost;
    public TextView city;
    public TextView duration;

}

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {

        View childView = arg1;
        if(childView == null || childView.getTag() == null){

            childView = mActivity.getLayoutInflater().inflate(R.layout.offer_cell, null);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.title = (TextView) childView.findViewById(R.id.offerTitle);
            viewHolder.img = (ImageView) childView.findViewById(R.id.offer_img);
            viewHolder.cost = (TextView) childView.findViewById(R.id.offerCost);
            viewHolder.city = (TextView) childView.findViewById(R.id.offerCity);
            viewHolder.duration = (TextView) childView.findViewById(R.id.offerDuration);
            /*viewHolder.niveau = (TextView) childView.findViewById(R.id.niveauTextView);
            viewHolder.poste = (TextView)childView.findViewById(R.id.postTextView);*/


            childView.setTag(viewHolder);
        }
        String url = "http://www.infos-mobiles.com/wp-content/uploads/2016/04/apple.png";
        ViewHolder holder = (ViewHolder) childView.getTag();
        holder.title.setText(mOffreList.get(arg0).getName());
        holder.cost.setText(String.valueOf(mOffreList.get(arg0).getCost() + " FCFA"));

        holder.img.setImageBitmap(bmp);


        /*holder.entreprise.setText("Entreprise: "+ mOffreList.get(arg0).getmEntreprise());
        holder.poste.setText(mOffreList.get(arg0).getmPoste());
        holder.niveau.setText(mOffreList.get(arg0).getmNiveauEtudes());*/
        return childView;
    }

}
