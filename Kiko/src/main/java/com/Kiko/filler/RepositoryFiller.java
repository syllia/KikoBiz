package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.Kiko.model.Categorie;
import com.Kiko.model.Offre;
import com.Kiko.model.SousCategorie;
import com.Kiko.repositories.CategorieRepository;
import com.Kiko.repositories.OfferRepository;
import com.Kiko.repositories.SousCategorieRepository;

@Component
public class RepositoryFiller {
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private SousCategorieRepository sousCategorieRepository;
	@Autowired
	private OfferRepository offerRepository;
	@Bean
	CommandLineRunner runner() {
		return args -> {
			fillCategrorieRepository(categorieRepository);
			fillSousCategorieRepository(sousCategorieRepository);
			fillOfferRepository(offerRepository);
		};
	}

	private void fillOfferRepository(OfferRepository offerRepository) {
		Offre offer = new Offre();
		offer.setCost(10);
		offer.setIdSousCategorie(1);
		offer.setOfferId(10);
		offerRepository.save(offer);
		Offre offer1 = new Offre();
		offer1.setIdSousCategorie(2);
		offer1.setOfferId(12);
		offerRepository.save(offer1);
		
	}

	private void fillSousCategorieRepository(SousCategorieRepository sousCategorieRepository) {
		sousCategorieRepository.save(new SousCategorie(1, 1, "Téléphones"));
		sousCategorieRepository.save(new SousCategorie(2, 1, "Ordinateur portables"));
		sousCategorieRepository.save(new SousCategorie(3, 1, "Ordinateur de bureau"));
		sousCategorieRepository.save(new SousCategorie(4, 2, "Voitures et camions"));
		sousCategorieRepository.save(new SousCategorie(5, 2, "Motos"));
		sousCategorieRepository.save(new SousCategorie(6, 2, "Vélos"));
		sousCategorieRepository.save(new SousCategorie(7, 3, "Lits,matelas"));
		sousCategorieRepository.save(new SousCategorie(8, 3, "Chaises,fauteuils"));
		sousCategorieRepository.save(new SousCategorie(9, 3, "Tables basses"));
		

	}

	private void fillCategrorieRepository(CategorieRepository categorieRepository) {
		categorieRepository.save(new Categorie(1, "Informatique et multimedia"));
		categorieRepository.save(new Categorie(2, "Autos et véhicules"));
		categorieRepository.save(new Categorie(3, "Meubles et décoration"));
		categorieRepository.save(new Categorie(4, "Livres"));
		categorieRepository.save(new Categorie(5, "Animaux"));
		categorieRepository.save(new Categorie(6, "Électroménager et vaisselles"));
		categorieRepository.save(new Categorie(7, "Immobilier"));
		categorieRepository.save(new Categorie(8, "Habillement et Bien-être"));
		categorieRepository.save(new Categorie(9, "Objets gratuits"));
		

	}
}
