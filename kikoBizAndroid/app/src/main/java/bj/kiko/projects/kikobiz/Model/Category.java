package bj.kiko.projects.kikobiz.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sylliamehou-loko on 16-05-19.
 */
public class Category {
    private String category;
    private ArrayList<SubCategory> subCategories;

    public Category() {
        category = "";
        this.subCategories = new ArrayList<SubCategory>();
    }

    public Category(String category, ArrayList<SubCategory> subCategories) {
        this.category = category;
        this.subCategories = subCategories;
    }

    public long getId(int position){
        return this.subCategories.get(position).getId();
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public ArrayList<String> getSubCategoriesList(){
        ArrayList<String> valuesList = new ArrayList<String>();
        for(int i = 0; i < this.subCategories.size(); i++){
            valuesList.add(this.subCategories.get(i).getName());
        }
        return valuesList;
    }
}
