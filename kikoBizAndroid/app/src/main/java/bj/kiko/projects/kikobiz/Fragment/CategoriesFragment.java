package bj.kiko.projects.kikobiz.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import bj.kiko.projects.kikobiz.Adapters.ExpandListAdapter;
import bj.kiko.projects.kikobiz.Model.Category;
import bj.kiko.projects.kikobiz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    private ExpandListAdapter ExpAdapter;
    private ExpandableListView ExpandList;
    private ArrayList<Category> list;
    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_categories, container, false);
        ExpandList = (ExpandableListView) view.findViewById(R.id.exp_list);
        list = new ArrayList<Category>();
        ArrayList<String> test = new ArrayList<String>();
        test.add("Alicia");
        test.add("Amina");
        test.add("Anaïs");
        ArrayList<String> test1 = new ArrayList<String>();
        test1.add("Bea");
        test1.add("Bertin");
        test1.add("Bravo");
        test1.add("Bryan");
        list.add(new Category("A", test));
        list.add(new Category("B", test1));
        list.add(new Category("C", test));
        list.add(new Category("D", test1));
        ExpAdapter = new ExpandListAdapter(this.getContext(), list);
        ExpandList.setAdapter(ExpAdapter);
        return  view;
    }

}
