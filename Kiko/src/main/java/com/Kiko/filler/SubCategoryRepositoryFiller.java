package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;

import com.Kiko.repositories.SubCategoryRepository;

public class SubCategoryRepositoryFiller {
	@Autowired
	public SubCategoryRepositoryFiller(SubCategoryRepository sousCategoryRepository) {
	}
}
