package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.Kiko.model.Category;
import com.Kiko.model.Offer;
import com.Kiko.model.SubCategory;
import com.Kiko.repositories.CategoryRepository;
import com.Kiko.repositories.OfferRepository;
import com.Kiko.repositories.SubCategoryRepository;

@Component
public class RepositoryFiller {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private OfferRepository offerRepository;
	@Bean
	CommandLineRunner runner() {
		return args -> {
			fillCategrorieRepository(categoryRepository);
			fillSousCategorieRepository(subCategoryRepository);
			fillOfferRepository(offerRepository);
		};
	}

	private void fillOfferRepository(OfferRepository offerRepository) {
		Offer offer = new Offer();
		offer.setCost(10);
		offer.setIdSousCategorie(1);
		offer.setOfferId(10);
		offerRepository.save(offer);
		Offer offer1 = new Offer();
		offer1.setIdSousCategorie(2);
		offer1.setOfferId(12);
		offerRepository.save(offer1);
		
	}

	private void fillSousCategorieRepository(SubCategoryRepository subCategoryRepository) {
		subCategoryRepository.save(new SubCategory(1, 1, "Téléphones"));
		subCategoryRepository.save(new SubCategory(2, 1, "Ordinateur portables"));
		subCategoryRepository.save(new SubCategory(3, 1, "Ordinateur de bureau"));
		subCategoryRepository.save(new SubCategory(4, 2, "Voitures et camions"));
		subCategoryRepository.save(new SubCategory(5, 2, "Motos"));
		subCategoryRepository.save(new SubCategory(6, 2, "Vélos"));
		subCategoryRepository.save(new SubCategory(7, 3, "Lits,matelas"));
		subCategoryRepository.save(new SubCategory(8, 3, "Chaises,fauteuils"));
		subCategoryRepository.save(new SubCategory(9, 3, "Tables basses"));
		

	}

	private void fillCategrorieRepository(CategoryRepository categoryRepository) {
		categoryRepository.save(new Category(1, "Informatique et multimedia"));
		categoryRepository.save(new Category(2, "Autos et véhicules"));
		categoryRepository.save(new Category(3, "Meubles et décoration"));
		categoryRepository.save(new Category(4, "Livres"));
		categoryRepository.save(new Category(5, "Animaux"));
		categoryRepository.save(new Category(6, "Électroménager et vaisselles"));
		categoryRepository.save(new Category(7, "Immobilier"));
		categoryRepository.save(new Category(8, "Habillement et Bien-être"));
		categoryRepository.save(new Category(9, "Objets gratuits"));
		

	}
}
