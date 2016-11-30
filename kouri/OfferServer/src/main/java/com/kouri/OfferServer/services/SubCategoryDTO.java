package com.kouri.OfferServer.services;

import com.kouri.OfferServer.domain.SubCategory;

public class SubCategoryDTO {
	public String name;

	public SubCategoryDTO(SubCategory subCategory) {
		this.name = subCategory.getName();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof SubCategoryDTO) {
			SubCategoryDTO subCategoryDTO = (SubCategoryDTO) object;
			return (this.name.equals(subCategoryDTO.name));
		} else {
			return false;
		}
	}
}
