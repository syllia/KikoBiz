package bj.kiko.projects.kikobiz.repositories;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import bj.kiko.projects.kikobiz.model.SousCategorie;
import bj.kiko.projects.kikobiz.model.app.SousCategorieApp;

public class SousCategorieRepository {

	public static List<SousCategorie> findAllByCategorie(String categorie) {
		List<SousCategorie> sousCategories = ofy().load().type(SousCategorie.class)
				.ancestor(KeyFactory.createKey(categorie, categorie)).list();
		return sousCategories;
	}

	public static SousCategorie save(SousCategorieApp SousCategorieApp) {

		SousCategorie sC = new SousCategorie(SousCategorieApp.getCategorie(), SousCategorieApp.getmName());

		ofy().save().entity(sC).now();
		return ofy().load().type(SousCategorie.class)
				.parent(KeyFactory.createKey(SousCategorieApp.getCategorie(), SousCategorieApp.getCategorie()))
				.id(sC.getId()).now();
	}

	public static SousCategorie modifyById(String idCategorie, long idSousCategorie, String newNameSousCategorie) {

		SousCategorie sc = ofy().load().type(SousCategorie.class).parent(KeyFactory.createKey(idCategorie, idCategorie))
				.id(idSousCategorie).now();

		if (sc != null) {
			sc.setmName(newNameSousCategorie);

			ofy().save().entity(sc).now();

			return ofy().load().type(SousCategorie.class).parent(KeyFactory.createKey(idCategorie, idCategorie))
					.id(idSousCategorie).now();
		}

		return sc;
	}

	public static SousCategorie deleteById(String idCategorie, Long idSousCategorie) {

		SousCategorie sc = ofy().load().type(SousCategorie.class).parent(KeyFactory.createKey(idCategorie, idCategorie))
				.id(idSousCategorie).now();
		if (sc != null) {

			ofy().delete().type(SousCategorie.class).parent(KeyFactory.createKey(idCategorie, idCategorie))
					.id(idSousCategorie).now();

			return ofy().load().type(SousCategorie.class).parent(KeyFactory.createKey(idCategorie, idCategorie))
					.id(idSousCategorie).now();
		}

		return sc;
	}
}
