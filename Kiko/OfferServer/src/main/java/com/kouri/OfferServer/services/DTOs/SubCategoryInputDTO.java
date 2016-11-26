package com.kouri.OfferServer.services.DTOs;

public class SubCategoryInputDTO {
	public String subCategoryName;
	public String categoryName;

	public SubCategoryInputDTO(String subCategoryName, String categoryName) {
		this.subCategoryName = subCategoryName;
		this.categoryName = categoryName;
	}

}
