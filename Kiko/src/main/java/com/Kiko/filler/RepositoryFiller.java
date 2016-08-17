package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.Kiko.model.Categorie;
import com.Kiko.model.SousCategorie;
import com.Kiko.repositories.CategorieRepository;
import com.Kiko.repositories.SousCategorieRepository;

@Component
public class RepositoryFiller {
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private SousCategorieRepository sousCategorieRepository;

	@Bean
	CommandLineRunner runner() {
		return args -> {
			fillCategrorieRepository(categorieRepository);
			fillSousCategorieRepository(sousCategorieRepository);
		};
	}

	private void fillSousCategorieRepository(SousCategorieRepository sousCategorieRepository) {
		sousCategorieRepository.save(new SousCategorie(1, 1, "test"));

	}

	private void fillCategrorieRepository(CategorieRepository categorieRepository) {
		categorieRepository.save(new Categorie(1, "informatique"));

	}
}
