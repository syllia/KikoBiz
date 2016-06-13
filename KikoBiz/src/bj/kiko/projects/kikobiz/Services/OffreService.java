package bj.kiko.projects.kikobiz.Services;


import java.util.List;

import bj.kiko.projects.kikobiz.model.app.OfferApp;
import bj.kiko.projects.kikobiz.repositories.OffreRepository;

public class OffreService {
	
	public static List<OfferApp> findAll(Long idSousCategorie) {
		return OffreRepository.findAllByIdSousCategorie(idSousCategorie);
	}
	
	public static OfferApp findById(Long idOffre) {
		return OffreRepository.findOfferById(idOffre);
	}
	/*public static List<OfferApp> Filtrer(Long idSousCategorie,String filtre)  {
		
		if(filtre.equals("costAsc")){
			//return OffreRepository.FiltreOffreByCostAsc(idSousCategorie);
		}
		else if(filtre.equals("costDsc")){
			//return OffreRepository.FiltreOffreByCostDesc(idSousCategorie);
		}
		else{
			//return OffreRepository.FiltreOffreByDate(idSousCategorie);
		}
	}*/
	 public static OfferApp save(OfferApp offre) {
		return OffreRepository.save(offre);
	}
}
