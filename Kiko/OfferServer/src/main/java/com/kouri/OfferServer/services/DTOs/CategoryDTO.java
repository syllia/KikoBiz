package com.kouri.OfferServer.services.DTOs;

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

	private void addSubCategoryDTO(List<SubCategory> subCategories) {
		for (SubCategory subCategoryRef : subCategories) {
			this.subCategoryDTOs.add(new SubCategoryDTO(subCategoryRef));
		}
	}

	public List<SubCategory> convertFromSubCategoryDTOToSubCategoryList() {
		List<SubCategory> subCategories = new ArrayList<>();
		for (SubCategoryDTO subCategoryDTORef : this.subCategoryDTOs) {
			subCategories.add(new SubCategory(subCategoryDTORef.name));
		}
		return subCategories;
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
