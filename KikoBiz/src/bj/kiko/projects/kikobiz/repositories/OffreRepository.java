package bj.kiko.projects.kikobiz.repositories;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;
import bj.kiko.projects.kikobiz.model.Offre;
import bj.kiko.projects.kikobiz.model.Utils.Utils;
import bj.kiko.projects.kikobiz.model.app.OfferApp;



public class OffreRepository {
	static {
		ObjectifyService.register(Offre.class);
	}
	public static List<OfferApp> findAllByIdSousCategorie(Long idSousCategorie){

		/*List <String> s =new ArrayList<String>();
		s.add("dddddd");
		s.add("dddddffffd");
		Offre off =new Offre(4L,"type",
				"titleP10",5000,"fffff",5,s);

		off.setRealPriority(10);


		ofy().save().entity(off)
		.now();*/

		List<Offre> offres = ofy().load().type(Offre.class)
				.ancestor(KeyFactory.createKey("sousCategorie", idSousCategorie))
				.filter("endDate >=", new Date())
				.order("-endDate")
				.order("-realPriority")
				.order("-startDate")
				.list();
		List<OfferApp> offersApp= new ArrayList<>();
		for (Offre o : offres){
			offersApp.add(new OfferApp(o.getSubCategoryId(),o.getTypeOffre(),
					o.getStartDate().toString(),o.getTitle(),o.getCost(),o.getDescription(),o.getNbViews(),o.getPictures()));
		}
		return offersApp;
	}
	public static void save(OfferApp oA){

		//ofy().save().entity(new Offre(p_offre.getName(),p_offre.getMessage())).now();
	}
	public static List<OfferApp> FiltreOffreByCostDesc(Long idSousCategorie){

		List<Offre> offres = ofy().load().type(Offre.class)
				.ancestor(KeyFactory.createKey("sousCategorie", idSousCategorie))
				.filter("endDate >=", new Date())
				.order("-cost")
				.list();
		List<OfferApp> offersApp= new ArrayList<>();
		for (Offre o : offres){
			offersApp.add(new OfferApp(o.getSubCategoryId(),o.getTypeOffre(),
					o.getStartDate().toString(),o.getTitle(),o.getCost(),o.getDescription(),o.getNbViews(),o.getPictures()));
		}
		return offersApp;
	}
	public static List<OfferApp> FiltreOffreByCostAsc(Long idSousCategorie) {
		List<Offre> offres = ofy().load().type(Offre.class)
				.ancestor(KeyFactory.createKey("sousCategorie", idSousCategorie))
				.filter("endDate >=", new Date())
				.order("-endDate")
				.order("cost")
				.list();
		List<OfferApp> offersApp= new ArrayList<>();
		for (Offre o : offres){
			offersApp.add(new OfferApp(o.getSubCategoryId(),o.getTypeOffre(),
					o.getStartDate().toString(),o.getTitle(),o.getCost(),o.getDescription(),o.getNbViews(),o.getPictures()));
		}
		return offersApp;
	}
	public static List<OfferApp> FiltreOffreByDate(Long idSousCategorie){
		List<Offre> offres = ofy().load().type(Offre.class)
				.ancestor(KeyFactory.createKey("sousCategorie", idSousCategorie))
				.filter("endDate >=", new Date())
				.order("-endDate")
				.list();
		List<OfferApp> offersApp= new ArrayList<>();
		for (Offre o : offres){
			offersApp.add(new OfferApp(o.getSubCategoryId(),o.getTypeOffre(),
					o.getStartDate().toString(),o.getTitle(),o.getCost(),o.getDescription(),o.getNbViews(),o.getPictures()));
		}
		return offersApp;
	}

}
