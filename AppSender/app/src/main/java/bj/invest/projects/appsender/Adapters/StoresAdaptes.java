package bj.invest.projects.appsender.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import bj.invest.projects.appsender.R;

/**
 * Created by sylliamehou-loko on 16-09-30.
 */
public class StoresAdaptes  extends ArrayAdapter<String>  {
        private ArrayList<String> mStoresList;
        private Activity mActivity;

        public StoresAdaptes(Activity inActivity, ArrayList<String> inFiltreList){
            super(inActivity, R.layout.activity_store, inFiltreList);
            this.mActivity = inActivity;
            mStoresList =inFiltreList;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mStoresList.size();
        }

        @Override
        public String getItem(int arg0) {
            // TODO Auto-generated method stub
            return mStoresList.get(arg0);
        }

        public ArrayList<String> getList() {
            return mStoresList;
        }
        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        class ViewHolder {
            public TextView store;

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

                childView = mActivity.getLayoutInflater().inflate(R.layout.cell_store, null);

                ViewHolder viewHolder = new ViewHolder();

                viewHolder.store = (TextView) childView.findViewById(R.id.storeTest);

                childView.setTag(viewHolder);
            }

            ViewHolder holder = (ViewHolder) childView.getTag();

            holder.store.setText(mStoresList.get(arg0));

            return childView;
        }
    }

