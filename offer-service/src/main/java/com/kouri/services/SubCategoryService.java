package com.kouri.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouri.domain.Category;
import com.kouri.domain.CategoryRepository;
import com.kouri.domain.SubCategory;
import com.kouri.domain.SubCategoryRepository;

@Service
public class SubCategoryService {
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public SubCategoryService() {
	}

	public SubCategoryService(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
		this.subCategoryRepository = subCategoryRepository;
	}

	public List<SubCategory> findAll() {
		return subCategoryRepository.findAll();
	}

	public SubCategory findById(String id) {
		UUID uid = UUID.fromString(id);
		return subCategoryRepository.getOne(uid);
	}

	public List<SubCategory> findByCategory(String idCategory) throws CategoryNotExistExecption {
		UUID uid = UUID.fromString(idCategory);
		if (categoryRepository.exists(uid)) {
			Category category = categoryRepository.getOne(uid);
			return subCategoryRepository.findByCategory(category);

		}
		throw new CategoryNotExistExecption();
	}

}
