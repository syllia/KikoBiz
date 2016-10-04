package bj.invest.projects.appsender.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import bj.invest.projects.appsender.Model.Customer;
import bj.invest.projects.appsender.R;
import bj.invest.projects.appsender.Util;

/**
 * Created by sylliamehou-loko on 16-09-19.
 */

public class SimpleListAdapter extends ArrayAdapter<Customer> {

    private ArrayList<Customer> listOfCustomers;
    private Activity mActivity;
    private ListAdapterListener mListener;


    public SimpleListAdapter(Activity inActivity, ArrayList<Customer> inFiltreList, ListAdapterListener listener){
        super(inActivity, R.layout.fragment_customer_list, inFiltreList);
        this.mActivity = inActivity;
        listOfCustomers =inFiltreList;
        mListener = listener;
    }


    public interface ListAdapterListener { // create an interface
        void onClickAtButton(int position, int buttonType); // create callback function
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listOfCustomers.size();
    }

    @Override
    public Customer getItem(int arg0) {
        // TODO Auto-generated method stub
        return listOfCustomers.get(arg0);
    }

    public ArrayList<Customer> getList() {
        return listOfCustomers;
    }
    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }
    public void add(JsonObject inJson){
        Customer outItem = new Customer(inJson);
        listOfCustomers.add(outItem);
    }


    class ViewHolder {
        public TextView PersonName;
        public TextView PersonNumber;
        public Button delete;
        public Customer currentCustomer;
        public Button update;
        public TextView nbBills;
        public TextView billLastDate;
        public TextView status;

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
            viewHolder.status = (TextView) childView.findViewById(R.id.statusTextView);
            viewHolder.nbBills = (TextView) childView.findViewById(R.id.nbPurchaseTextView);
            viewHolder.billLastDate = (TextView) childView.findViewById(R.id.lastDateTextView);

            //viewHolder.switchBtn = (ToggleButton) childView.findViewById(R.id.filtre_switch);
            childView.setTag(viewHolder);
            viewHolder.currentCustomer = listOfCustomers.get(arg0);

            viewHolder.update = (Button)childView.findViewById(R.id.ok_button);
            viewHolder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickAtButton(arg0, 0);
                }
            });
            viewHolder.delete = (Button)childView.findViewById(R.id.delete_button);
            viewHolder.delete.setTag(viewHolder.currentCustomer);
            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickAtButton(arg0, 1);
                }
            });



        }
        ViewHolder holder = (ViewHolder) childView.getTag();

        holder.PersonName.setText(listOfCustomers.get(arg0).getName());
        holder.PersonNumber.setText(listOfCustomers.get(arg0).getPhoneNumber());
        holder.billLastDate.setText(listOfCustomers.get(arg0).getLastBillDate());
        holder.nbBills.setText(Integer.toString(listOfCustomers.get(arg0).getNumberBill()));
        holder.status.setText(listOfCustomers.get(arg0).getInfo());

        return childView;
    //}
    }



}