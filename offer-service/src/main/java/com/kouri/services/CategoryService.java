package com.kouri.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouri.domain.Category;
import com.kouri.domain.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryService() {
	}

	public CategoryService(CategoryRepository CategoryRepository) {
		this.categoryRepository = CategoryRepository;
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(String id) {
		UUID uid = UUID.fromString(id);
		return categoryRepository.getOne(uid);
	}
}
