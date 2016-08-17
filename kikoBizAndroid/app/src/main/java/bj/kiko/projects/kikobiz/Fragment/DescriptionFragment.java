package bj.kiko.projects.kikobiz.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Vector;

import bj.kiko.projects.kikobiz.Model.Offer;
import bj.kiko.projects.kikobiz.R;
import bj.kiko.projects.kikobiz.Util.LocalSavedPref;


public class DescriptionFragment extends Fragment {
    TextView desc;
    LocalSavedPref lclSaveReqForFav;
    ToggleButton fav;
    ArrayList<String> listOfSelectedOffer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle("Description");

        lclSaveReqForFav = new LocalSavedPref(getContext());
        listOfSelectedOffer = new ArrayList<String>(lclSaveReqForFav.getFavId());


        View rootView = inflater.inflate(R.layout.fragment_description, container, false);
        final Offer offerToDescribe = getArguments().getParcelable("Offre");
        desc = (TextView)rootView.findViewById(R.id.descTextView);
        String description = "title "+ offerToDescribe.getName() +
                "\n id: "+ offerToDescribe.getOfferId() + "\n cost: " + offerToDescribe.getCost() ;

        desc.setText(description);

        fav = (ToggleButton)rootView.findViewById(R.id.toggleFav);

        fav.setChecked(listOfSelectedOffer.contains(Long.toString(offerToDescribe.getOfferId())));


        fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addToListOfSelected(Long.toString(offerToDescribe.getOfferId()));
                    Log.d("ID of selectedFav :", Long.toString(offerToDescribe.getOfferId()));
                } else{
                    removeFromListOfSelected(Long.toString(offerToDescribe.getOfferId()));
                }
            }
        });

        return rootView;
    }

    public void addToListOfSelected(String aFiltre) {
        if (!listOfSelectedOffer.contains(aFiltre)) {
            listOfSelectedOffer.add(aFiltre);
        }
    }

    public void removeFromListOfSelected(String aFiltre){
        if(listOfSelectedOffer.contains(aFiltre)){
            listOfSelectedOffer.remove(aFiltre);
        }
    }

    public void saveReglage(){
        ArrayList<String> aList = new ArrayList<>(listOfSelectedOffer);
        //Recuperer tout les switch sur on
        lclSaveReqForFav.setFavId(aList);
        Log.d("saveReglage: ", Integer.toString(aList.size()));
    }

    @Override
    public void onStop(){
        saveReglage();
        super.onStop();
    }

    public void onBackPressed() {
        saveReglage();
        //getFragmentManager().popBackStack();

    }


}