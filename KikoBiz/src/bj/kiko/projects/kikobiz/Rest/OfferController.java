package bj.kiko.projects.kikobiz.Rest;


import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.ObjectifyService;

import bj.kiko.projects.kikobiz.Services.OffreService;

import bj.kiko.projects.kikobiz.model.Offre;
import bj.kiko.projects.kikobiz.model.app.OfferApp;


@Api(name = "monapi")
public class OfferController {
	static {
		ObjectifyService.register(Offre.class);
	}

	@ApiMethod(path = "offres/{idSousCategorie}", httpMethod = ApiMethod.HttpMethod.GET)
	public List<OfferApp> OfferList(@Named("idSousCategorie") Long idSousCategorie)  {
		// Récupère toute les offres d'une sousCategorie...
		List<OfferApp> offres = OffreService.findAll(idSousCategorie);
		return offres;
	}
	/*@ApiMethod(path = "offres/{idSousCategorie}/{type}", httpMethod = ApiMethod.HttpMethod.GET)
	public List<OfferApp> OderOfferList(@Named("idSousCategorie") Long idSousCategorie,@Named("type") String type) {
		// Récupère toute les offres d'une sousCategorie...
		System.out.println(type);
		List<OfferApp> offres = OffreService.Filtrer(idSousCategorie, type);
		return offres;
	}*/
	@ApiMethod(name = "offres.insert", path = "offres", httpMethod = ApiMethod.HttpMethod.POST)
	public OfferApp save(OfferApp oA) {
		
		return OffreService.save(oA);
	}


}
