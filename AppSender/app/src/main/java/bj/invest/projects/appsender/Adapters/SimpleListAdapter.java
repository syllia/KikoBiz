package bj.invest.projects.appsender.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import bj.invest.projects.appsender.Model.Person;
import bj.invest.projects.appsender.R;

/**
 * Created by sylliamehou-loko on 16-09-19.
 */

public class SimpleListAdapter extends ArrayAdapter<Person> {

    private ArrayList<Person> Person;
    private Activity mActivity;

    public SimpleListAdapter(Activity inActivity, ArrayList<Person> inFiltreList){
        super(inActivity, R.layout.fragment_customer_list, inFiltreList);
        this.mActivity = inActivity;
        Person =inFiltreList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Person.size();
    }

    @Override
    public Person getItem(int arg0) {
        // TODO Auto-generated method stub
        return Person.get(arg0);
    }

    public ArrayList<Person> getList() {
        return Person;
    }
    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    class ViewHolder {
        public TextView PersonName;
        public TextView PersonNumber;
        public Button delete;

    }

    @Override
    public int getViewTypeCount() {
        //Count=Size of ArrayList.
        return 13;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {

        View childView = arg1;
        if(childView == null || childView.getTag() == null){

            childView = mActivity.getLayoutInflater().inflate(R.layout.customer_cell, null);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.PersonName = (TextView) childView.findViewById(R.id.customerName);
            viewHolder.PersonNumber = (TextView) childView.findViewById(R.id.customerPhone);
            //viewHolder.switchBtn = (ToggleButton) childView.findViewById(R.id.filtre_switch);
            childView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) childView.getTag();

        holder.PersonName.setText(Person.get(arg0).getName());
        holder.PersonNumber.setText(Person.get(arg0).getPhoneNumber());
        holder.delete = (Button)childView.findViewById(R.id.delete_button);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Person.remove(arg0);
            }
        });
        this.notifyDataSetChanged();

        return childView;
    //}
}

 /*   public void addToListOfSelected(String aFiltre) {
        if (!listOfSelectedFiltre.contains(aFiltre)) {
            listOfSelectedFiltre.add(aFiltre);
            mFiltreAdapter.notifyDataSetChanged();
        }
    }

    public void removeToListOfSelected(String aFiltre){
        if(listOfSelectedFiltre.contains(aFiltre)){
            listOfSelectedFiltre.remove(aFiltre);
            mFiltreAdapter.notifyDataSetChanged();
        }
    }

    public void add(JSONObject inJson){
        Secteur outItem = new Secteur(inJson);
        listOfFiltre.add(outItem.getmName());
    }

    public void saveReglage(){
        Vector<String> vector = new Vector<String>(listOfSelectedFiltre);
        //Recuperer tout les switch sur on
        lclSaveReq.setTag(vector);
    }

    @Override
    public void onStop(){
        saveReglage();
        super.onStop();
    }

    public void onBackPressed() {
        saveReglage();
        getFragmentManager().popBackStack();

    }*/

}