package bj.kiko.projects.kikobiz.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sylliamehou-loko on 16-05-19.
 */
public class Category {
    private String category;
    private ArrayList<String> subCategories;

    public Category() {
        category = "";
        this.subCategories = new ArrayList<String>();
    }

    public Category(String category, ArrayList<String> subCategories) {
        this.category = category;
        this.subCategories = subCategories;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<String> subCategories) {
        this.subCategories = subCategories;
    }
}
