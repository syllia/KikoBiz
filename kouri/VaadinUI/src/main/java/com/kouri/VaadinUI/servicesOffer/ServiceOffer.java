package com.kouri.VaadinUI.servicesOffer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouri.OfferServer.domain.Category;
import com.kouri.OfferServer.domain.SubCategoryAlreadyRegisteredException;
import com.kouri.OfferServer.services.CategoryAlreadyRegisterException;
import com.kouri.OfferServer.services.CategoryDTO;
import com.kouri.OfferServer.services.CategoryService;
@Service
public class ServiceOffer {

	CategoryService categoryService;

	@Autowired
	public ServiceOffer(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void postCategory() {

		try {
			categoryService.saveCategory(new CategoryDTO(new Category("dddd")));
		} catch (CategoryAlreadyRegisterException | SubCategoryAlreadyRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getCategory() {
		List<CategoryDTO> categoryDTOs = categoryService.findAll();
		for (CategoryDTO categoryDTO : categoryDTOs) {
			System.out.println(categoryDTO.name);
		}
	}

}
