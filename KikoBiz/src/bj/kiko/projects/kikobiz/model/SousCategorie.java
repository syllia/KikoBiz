package bj.kiko.projects.kikobiz.model;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Cache()
public class SousCategorie {
	@Id String id;
	@Parent Key categorie;
	private String mName;
	
	public SousCategorie(){
		
	}
	public SousCategorie(String name, Key parent){
		this.mName=name;
		this.categorie = parent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
