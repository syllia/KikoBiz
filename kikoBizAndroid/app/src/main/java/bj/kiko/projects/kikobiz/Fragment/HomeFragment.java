package bj.kiko.projects.kikobiz.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bj.kiko.projects.kikobiz.R;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    Button consult;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle("Accueil");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        consult = (Button) view.findViewById(R.id.consultButton);
        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoriesFragment test = new CategoriesFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, test).addToBackStack("categories").commit();
            }
        });

        return view;
    }

}
