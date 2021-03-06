package com.kouri.OfferServer.services;

import java.util.ArrayList;
import java.util.List;

import com.kouri.OfferServer.domain.Category;
import com.kouri.OfferServer.domain.SubCategory;

public class CategoryDTO {
	public String name;
	public List<SubCategoryDTO> subCategoryDTOs = new ArrayList<SubCategoryDTO>();

	public CategoryDTO(Category category) {
		this.name = category.getName();
		addSubCategoryDTO(category.getSubCategories());
	}

	public CategoryDTO(String name) {
		this.name = name;
		this.subCategoryDTOs = new ArrayList<>();
	}

	private void addSubCategoryDTO(List<SubCategory> subCategories) {
		for (SubCategory subCategoryRef : subCategories) {
			this.subCategoryDTOs.add(new SubCategoryDTO(subCategoryRef));
		}
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof CategoryDTO) {
			CategoryDTO categoryDTO = (CategoryDTO) object;
			return (this.name.equals(categoryDTO.name) && this.subCategoryDTOs.equals(categoryDTO.subCategoryDTOs));
		} else {
			return false;
		}
	}
}
