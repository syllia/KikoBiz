package bj.kiko.projects.kikobiz.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import bj.kiko.projects.kikobiz.LoginActivity;
import bj.kiko.projects.kikobiz.Model.Offer;
import bj.kiko.projects.kikobiz.R;
import bj.kiko.projects.kikobiz.Util.LocalSavedPref;


public class ParametresFragment extends Fragment {
    private ArrayList<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list= new ArrayList<>();
        final LocalSavedPref lc = new LocalSavedPref(getContext());
        if (lc.getNumero().equals("")){
            list.add("Ouvrir une session");
        }else {
            list.add("Fermer la session");
        }
        list.add("Recommander à un ami");
        list.add("Informations et Aide");
        getActivity().setTitle(getActivity().getResources().getString(R.string.FragmentParametresName));
        View view = inflater.inflate(R.layout.fragment_parametres, container, false);
        ListView listView = (ListView) view.findViewById(R.id.param_listView);
        final ArrayAdapter adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,list);
        //setListAdapter(adapter);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (adapter.getItem(arg2).toString().equals("Ouvrir une session")) {
                    lc.setNumero("");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("name",getActivity().getResources().getString(R.string.FragmentParametresName));
                    startActivity(intent);
                } else if (adapter.getItem(arg2).toString().equals("Fermer la session")) {
                    lc.setNumero("");
                    reload();
                }
            }
        });

        return view;
    }
    private void reload(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}
