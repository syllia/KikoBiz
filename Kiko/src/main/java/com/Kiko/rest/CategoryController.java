package com.Kiko.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kiko.model.Category;
import com.Kiko.services.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> findAll() {

		List<Category> categories = categoryService.findAll();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> getById(@PathVariable int id) {

		Category category = categoryService.findById(id);

		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
}
