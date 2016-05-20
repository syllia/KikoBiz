package bj.kiko.projects.kikobiz.model.app;

public class SousCategorieApp {
	private String id;
	private String categorie;
	private String mName;

	public SousCategorieApp() {

	}

	public SousCategorieApp(String categorie, String name) {
		this.mName = name;
		this.categorie = categorie;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}
}
