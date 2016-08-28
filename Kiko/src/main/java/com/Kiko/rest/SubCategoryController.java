package com.Kiko.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kiko.model.SubCategory;
import com.Kiko.services.SubCategoryService;



@RestController
public class SubCategoryController {
@Autowired private SubCategoryService subCategoryService;

	
	@RequestMapping(value="/souscategories", method = RequestMethod.GET)
	public ResponseEntity<List<SubCategory>> findAll(){
		List<SubCategory> subCategories =  subCategoryService.findAll();
		return new ResponseEntity<List<SubCategory>>(subCategories, HttpStatus.OK);
	}
	
	@RequestMapping(value="/souscategoriesparcategorie/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<SubCategory>> getByIdCategorie(@PathVariable int id){
		List<SubCategory> subCategory =  subCategoryService.findAllByIdCategorie(id);
		return new ResponseEntity<List<SubCategory>>(subCategory, HttpStatus.OK);
	}


}
