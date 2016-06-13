package bj.kiko.projects.kikobiz.Model;

/**
 * Created by sylliamehou-loko on 16-05-30.
 */
public class SubCategory {
    private long id;
    private String name;

    public SubCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
