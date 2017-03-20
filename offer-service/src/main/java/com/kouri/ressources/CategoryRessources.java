package com.kouri.ressources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kouri.domain.Category;
import com.kouri.domain.SubCategory;
import com.kouri.services.CategoryNotExistExecption;
import com.kouri.services.CategoryService;
import com.kouri.services.ElementIsAlreadyAddedExecption;
import com.kouri.services.SubCategoryService;

@RestController
public class CategoryRessources {

	@Autowired
	private CategoryService categoryService;
	private SubCategoryService subCategoryService;

	@RequestMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.findAll();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public ResponseEntity<?> addNewcategories(@RequestBody Category nameCategory) {
		Category category;
		try {
			category = categoryService.persist(nameCategory.getName());
			return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (ElementIsAlreadyAddedExecption e) {
			return new ResponseEntity<>("Category already exist", HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/categories/{id}/subcategories", method = RequestMethod.GET)
	public ResponseEntity<List<SubCategory>> getChildOfCategories(@RequestParam(name = "id") String idCategory) {
		try {
			List<SubCategory> subCategories = subCategoryService.findByCategory(idCategory);
			return new ResponseEntity<List<SubCategory>>(subCategories, HttpStatus.OK);
		} catch (CategoryNotExistExecption e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
