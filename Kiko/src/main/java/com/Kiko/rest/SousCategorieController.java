package com.Kiko.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kiko.model.SousCategorie;
import com.Kiko.services.SousCategorieService;



@RestController
public class SousCategorieController {
@Autowired private SousCategorieService sousCategorieService;

	
	@RequestMapping(value="/sousCategories", method = RequestMethod.GET)
	public ResponseEntity<List<SousCategorie>> findAll(){
		
		List<SousCategorie> sousCategories =  sousCategorieService.findAll();
		return new ResponseEntity<List<SousCategorie>>(sousCategories, HttpStatus.OK);
	}
	

	@RequestMapping(value="/sousCategories/{id}", method = RequestMethod.GET)
	public ResponseEntity<SousCategorie> getById(@PathVariable int id){
		
		SousCategorie sousCategorie =  sousCategorieService.findById(id);
		
		return new ResponseEntity<SousCategorie>(sousCategorie, HttpStatus.OK);
	}
	/*@RequestMapping(value="/sousCategorieByCategorie/{id}", method = RequestMethod.GET)
	public ResponseEntity<SousCategorie> getByIdCategorie(@PathVariable int id){
		
		SousCategorie sousCategorie =  sousCategorieService.findByIdCategorie(id);
		
		return new ResponseEntity<SousCategorie>(sousCategorie, HttpStatus.OK);
	}*/


}
