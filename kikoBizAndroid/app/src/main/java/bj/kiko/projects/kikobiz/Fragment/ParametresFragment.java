package bj.kiko.projects.kikobiz.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bj.kiko.projects.kikobiz.R;


public class ParametresFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getActivity().getResources().getString(R.string.FragmentParametresName));
        View view = inflater.inflate(R.layout.fragment_parametres, container, false);
        return view;
    }
}
