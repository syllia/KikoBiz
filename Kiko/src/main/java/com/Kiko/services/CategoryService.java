package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.Category;
import com.Kiko.repositories.CategoryRepository;
@Service
public class CategoryService {
	private CategoryRepository categoryRepository;
	@Autowired 
	public CategoryService(CategoryRepository CategoryRepository){
		this.categoryRepository = CategoryRepository;
	}
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(int id) {
		return categoryRepository.getOne(id);
	}
}
