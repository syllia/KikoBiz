package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;

import com.Kiko.repositories.CategorieRepository;



public class CategorieRepositoryFiller {
	@Autowired
	public CategorieRepositoryFiller(CategorieRepository CategoryRepository) {
	}
}
