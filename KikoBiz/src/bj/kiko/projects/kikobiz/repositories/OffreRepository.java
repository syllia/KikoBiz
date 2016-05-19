package bj.kiko.projects.kikobiz.repositories;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;
import bj.kiko.projects.kikobiz.model.Offre;



public class OffreRepository {
	static {
        ObjectifyService.register(Offre.class);
    }
	
	public static List<Offre> findAll(){
		List<Offre> offres = ofy().load().type(Offre.class)
						.ancestor(KeyFactory.createKey("TouteOffres", "nosOffres"))
						.list();
		return offres;
	}
	public static void save(Offre p_offre){
		 
		//ofy().save().entity(new Offre(p_offre.getName(),p_offre.getMessage())).now();
	}
	
}
