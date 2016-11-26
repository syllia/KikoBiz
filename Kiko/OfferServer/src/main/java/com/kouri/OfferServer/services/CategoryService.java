package com.kouri.OfferServer.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouri.OfferServer.domain.Category;
import com.kouri.OfferServer.domain.CategoryRepository;
import com.kouri.OfferServer.domain.SubCategory;
import com.kouri.OfferServer.domain.SubCategoryAlreadyRegisteredException;
import com.kouri.OfferServer.services.DTOs.CategoryDTO;
import com.kouri.OfferServer.services.DTOs.SubCategoryInputDTO;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository CategoryRepository) {
		this.categoryRepository = CategoryRepository;
	}

	public List<CategoryDTO> findAll() {
		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
		for (Category categoryRef : categoryRepository.findAll()) {
			categoryDTOs.add(new CategoryDTO(categoryRef));
		}
		return categoryDTOs;
	}

	public void saveCategory(CategoryDTO categoryDTO)
			throws CategoryAlreadyRegisterException, SubCategoryAlreadyRegisteredException {
		if (categoryRepository.findByName(categoryDTO.name) == null) {
			categoryRepository
					.save(new Category(categoryDTO.name, categoryDTO.convertFromSubCategoryDTOToSubCategoryList()));
		} else {
			throw new CategoryAlreadyRegisterException();
		}
	}

	public void addSubCategory(SubCategoryInputDTO subCategoryInputDTO)
			throws CategoryNotFounException, SubCategoryAlreadyRegisteredException {
		Category category = categoryRepository.findByName(subCategoryInputDTO.categoryName);
		if (category != null) {
			category.addSubCategory(new SubCategory(subCategoryInputDTO.subCategoryName));
			categoryRepository.save(category);

		} else {
			throw new CategoryNotFounException();
		}
	}

}
