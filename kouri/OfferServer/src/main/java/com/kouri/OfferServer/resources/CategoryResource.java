package com.kouri.OfferServer.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kouri.OfferServer.domain.SubCategoryAlreadyRegisteredException;
import com.kouri.OfferServer.services.CategoryAlreadyRegisterException;
import com.kouri.OfferServer.services.CategoryDTO;
import com.kouri.OfferServer.services.CategoryNotFounException;
import com.kouri.OfferServer.services.CategoryService;
import com.kouri.OfferServer.services.SubCategoryInputDTO;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	private CategoryService categoryService;

	@Autowired
	public CategoryResource(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<CategoryDTO>> findAll() {
		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> saveCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
		try {
			categoryService.saveCategory(categoryDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CategoryAlreadyRegisterException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (SubCategoryAlreadyRegisteredException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> addSubCategory(SubCategoryInputDTO subCategoryInputDTO) {
		try {
			categoryService.addSubCategory(subCategoryInputDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CategoryNotFounException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (SubCategoryAlreadyRegisteredException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

}
