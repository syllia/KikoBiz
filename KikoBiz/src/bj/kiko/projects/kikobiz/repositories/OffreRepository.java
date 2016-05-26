package bj.kiko.projects.kikobiz.repositories;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;
import bj.kiko.projects.kikobiz.model.Offre;
import bj.kiko.projects.kikobiz.model.app.OfferApp;

public class OffreRepository {
	static {
		ObjectifyService.register(Offre.class);
	}

	public static List<OfferApp> findAllByIdSousCategorie(Long idSousCategorie) {

		List<Offre> offres = ofy().load().type(Offre.class).ancestor(KeyFactory.createKey("offre", "offres"))
				.filter("subCategoryId", idSousCategorie).filter("endDate >=", new Date()).order("-endDate")
				.order("-realPriority").order("-startDate").list();
		List<OfferApp> offersApp = new ArrayList<>();
		for (Offre o : offres) {
			OfferApp oa = new OfferApp(o.getUser(), o.getSubCategoryId(), o.getTypeOffre(), o.getTitle(), o.getCost(),
					o.getDescription(), o.getNbViews(), o.getPictures());
			oa.setStartDate(o.getStartDate().toString());
			oa.setId(o.getId());
			offersApp.add(oa);
		}
		return offersApp;
	}

	public static OfferApp save(OfferApp oA) {
		Offre o = new Offre(oA.getUser(), oA.getSubCategoryId(), oA.getTypeOffre(), oA.getTitle(), oA.getCost(),
				oA.getDescription(), oA.getNbViews(), oA.getPictures());

		ofy().save().entity(o).now();
		o = ofy().load().type(Offre.class).parent(KeyFactory.createKey("offre", "offres")).id(o.getId()).now();
		if (o != null) {
			OfferApp of = new OfferApp(o.getUser(), o.getSubCategoryId(), o.getTypeOffre(), o.getTitle(), o.getCost(),
					o.getDescription(), o.getNbViews(), o.getPictures());
			of.setStartDate(o.getStartDate().toString());
			of.setId(o.getId());
			return of;
		} else {
			return null;
		}
	}
	/*
	 * public static List<OfferApp> FiltreOffreByCostDesc(Long idSousCategorie){
	 * 
	 * List<Offre> offres = ofy().load().type(Offre.class)
	 * .ancestor(KeyFactory.createKey("sousCategorie", idSousCategorie))
	 * .filter("endDate >=", new Date()) .order("-cost") .list(); List<OfferApp>
	 * offersApp= new ArrayList<>(); for (Offre o : offres){ offersApp.add(new
	 * OfferApp(o.getUser(),o.getSubCategoryId(),o.getTypeOffre(),
	 * o.getStartDate().toString(),o.getTitle(),o.getCost(),o.getDescription(),o
	 * .getNbViews(),o.getPictures())); } return offersApp; } public static
	 * List<OfferApp> FiltreOffreByCostAsc(Long idSousCategorie) { List<Offre>
	 * offres = ofy().load().type(Offre.class)
	 * .ancestor(KeyFactory.createKey("sousCategorie", idSousCategorie))
	 * .filter("endDate >=", new Date()) .order("-endDate") .order("cost")
	 * .list(); List<OfferApp> offersApp= new ArrayList<>(); for (Offre o :
	 * offres){ offersApp.add(new
	 * OfferApp(o.getUser(),o.getSubCategoryId(),o.getTypeOffre(),
	 * o.getStartDate().toString(),o.getTitle(),o.getCost(),o.getDescription(),o
	 * .getNbViews(),o.getPictures())); } return offersApp; } public static
	 * List<OfferApp> FiltreOffreByDate(Long idSousCategorie){ List<Offre>
	 * offres = ofy().load().type(Offre.class)
	 * .ancestor(KeyFactory.createKey("sousCategorie", idSousCategorie))
	 * .filter("endDate >=", new Date()) .order("-endDate") .list();
	 * List<OfferApp> offersApp= new ArrayList<>(); for (Offre o : offres){
	 * offersApp.add(new
	 * OfferApp(o.getUser(),o.getSubCategoryId(),o.getTypeOffre(),
	 * o.getStartDate().toString(),o.getTitle(),o.getCost(),o.getDescription(),o
	 * .getNbViews(),o.getPictures())); } return offersApp; }
	 */

}
