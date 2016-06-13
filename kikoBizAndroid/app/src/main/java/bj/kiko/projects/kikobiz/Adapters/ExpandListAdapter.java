package bj.kiko.projects.kikobiz.Adapters;

/**
 * Created by sylliamehou-loko on 16-05-19.
 */

        import java.util.ArrayList;
        import java.util.HashMap;

        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseExpandableListAdapter;
        import android.widget.TextView;

        import org.json.JSONObject;

        import bj.kiko.projects.kikobiz.Model.Category;
        import bj.kiko.projects.kikobiz.Model.SubCategory;
        import bj.kiko.projects.kikobiz.R;

public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Category> categories;


    public ExpandListAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public String getChild(int catPositon, int subPosition) {
        ArrayList<String> chList = categories.get(catPositon).getSubCategoriesList();
        return chList.get(subPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return categories.get(groupPosition).getSubCategories().get(childPosition).getId();
        //return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        String child = getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.subcategory_item, null);
        }
        //Log.d("TEST SYLLIA: ", child);
        TextView tv = (TextView) convertView.findViewById(R.id.subcategory_name);
        tv.setText(child);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<SubCategory> chList = categories.get(groupPosition).getSubCategories();
        return chList.size();
    }
    public void add(String catName, ArrayList<SubCategory> subCat){
        Category outItem = new Category(catName, subCat);
        this.categories.add(outItem);
    }

    @Override
    public Object getGroup(int groupPosition) {

        return categories.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Category category = (Category)getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.
                            LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.category_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.category_name);
        tv.setText(category.getCategory());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}