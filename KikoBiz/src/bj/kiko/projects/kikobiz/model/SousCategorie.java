package bj.kiko.projects.kikobiz.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Cache()
public class SousCategorie {
	@Id
	Long idSousCategorie;
	@Parent
	Key categorie;
	private String mName;

	public SousCategorie() {
	}

	public SousCategorie(String parent, String name) {
		this.mName = name;
		this.categorie = KeyFactory.createKey(parent, parent);
	}

	public Long getId() {
		return idSousCategorie;
	}

	public void setId(Long id) {
		this.idSousCategorie = id;
	}

	public Key getParent() {
		return categorie;
	}

	public void setParent(Key parent) {
		this.categorie = parent;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}
}
