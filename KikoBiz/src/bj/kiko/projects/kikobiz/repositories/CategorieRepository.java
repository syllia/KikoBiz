package bj.kiko.projects.kikobiz.repositories;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;

import bj.kiko.projects.kikobiz.model.Categorie;

public class CategorieRepository {
	static {
        ObjectifyService.register(Categorie.class);
    }
	
	public static List<Categorie> findAll(){
		List<Categorie> Categories = ofy().load().type(Categorie.class)
						.ancestor(KeyFactory.createKey("Categorie", "nosCategories"))
						.list();
		
		return Categories;
	}
	public static Categorie save(Categorie categorie){
		Categorie c=new Categorie(categorie.getId());
		
		ofy().save().entity(c).now();
		
		return ofy().load().type(Categorie.class)
				.parent(KeyFactory.createKey("Categorie", "nosCategories"))
				.id(categorie.getId()).now();
	}
	public static  Categorie modifyId(String id,String nvId){
		
		Categorie c =ofy().load().type(Categorie.class)
				.parent(KeyFactory.createKey("Categorie", "nosCategories"))
				.id(id).now();
		
		if (c!= null){
			c.setId(nvId);
			
			ofy().delete().type(Categorie.class)
			.parent(KeyFactory.createKey("Categorie", "nosCategories"))
			.id(id).now();
			
			ofy().save().entity(c).now();
			return ofy().load().type(Categorie.class)
					.parent(KeyFactory.createKey("Categorie", "nosCategories"))
					.id(nvId).now();
		}
		
		return c;
	}
public static  Categorie deleteById(String id){
		
		Categorie c =ofy().load().type(Categorie.class)
				.parent(KeyFactory.createKey("Categorie", "nosCategories"))
				.id(id).now();
		
		if (c!= null){
			
			ofy().delete().type(Categorie.class)
			.parent(KeyFactory.createKey("Categorie", "nosCategories"))
			.id(id).now();
			
			return ofy().load().type(Categorie.class)
					.parent(KeyFactory.createKey("Categorie", "nosCategories"))
					.id(id).now();
		}
		
		return c;
	}
	
	
}
