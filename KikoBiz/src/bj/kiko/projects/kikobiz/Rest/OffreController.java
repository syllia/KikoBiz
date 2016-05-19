package bj.kiko.projects.kikobiz.Rest;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import bj.kiko.projects.kikobiz.Services.OffreService;
import bj.kiko.projects.kikobiz.model.Offre;

@Api(name = "monapi")
public class OffreController {
	
	@ApiMethod(
	        path = "offres",
	        httpMethod = ApiMethod.HttpMethod.GET
	    )
	public List <Offre>listeOffres() {	
	    // Récupère toute les offres...
		List<Offre> offres = OffreService.findAll();
				
	    return offres;
	}
	@ApiMethod(
			name = "offre.insert",
	        path = "offres",
	        httpMethod = ApiMethod.HttpMethod.POST
	        
	    )
	public Offre save(Offre offre) {

		OffreService.save(offre);
		return offre;
	}
}
