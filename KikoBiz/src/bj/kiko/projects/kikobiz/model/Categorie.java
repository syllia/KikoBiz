package bj.kiko.projects.kikobiz.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Cache()
public class Categorie {
	@Id
	String id;
	@Parent
	Key parent;

	public Categorie() {

	}

	public Categorie(String id) {
		this.id = id;
		this.parent = KeyFactory.createKey("Categorie", "nosCategories");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Key getParent() {
		return parent;
	}

	public void setParent(Key parent) {
		this.parent = parent;
	}
}
