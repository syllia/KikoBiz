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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle(getActivity().getResources().getString(R.string.FragmentHomeName));
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

}
