package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;

import com.Kiko.repositories.CategoryRepository;



public class CategoryRepositoryFiller {
	@Autowired
	public CategoryRepositoryFiller(CategoryRepository CategoryRepository) {
	}
}
