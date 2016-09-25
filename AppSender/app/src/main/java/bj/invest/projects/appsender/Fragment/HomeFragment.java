package bj.invest.projects.appsender.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import bj.invest.projects.appsender.R;


public class HomeFragment extends Fragment {

    Button ListOfCustomers;
    ImageView imgList;

   // private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().setTitle("Accueil");
       // imgList = (ImageView)rootView.findViewById(R.id.customer_list_img);
        //View view = inflater.inflate(R.layout.fragment_accueil, container, false);
        ListOfCustomers = (Button) rootView.findViewById(R.id.ListOfCustomer);
        ListOfCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerList test = new CustomerList();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, test).addToBackStack("formulaire").commit();
            }
        });
        return rootView;
    }



 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
