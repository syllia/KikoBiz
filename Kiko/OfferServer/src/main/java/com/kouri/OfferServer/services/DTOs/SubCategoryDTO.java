package com.kouri.OfferServer.services.DTOs;

import com.kouri.OfferServer.domain.SubCategory;

public class SubCategoryDTO {
	public String name;
	public int id;

	public SubCategoryDTO(SubCategory subCategory) {
		this.name = subCategory.getName();
		this.id = subCategory.getIdSubCategory();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof SubCategoryDTO) {
			SubCategoryDTO subCategoryDTO = (SubCategoryDTO) object;
			return (this.name.equals(subCategoryDTO.name) && this.id == subCategoryDTO.id);
		} else {
			return false;
		}
	}
}
