package com.Kiko.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kiko.model.City;
import com.Kiko.services.CityService;

@RestController
public class CityController {
@Autowired private CityService cityService;

	
	@RequestMapping(value="/cities", method = RequestMethod.GET)
	public ResponseEntity<List<City>> findAll(){
		List<City> subCategories =  cityService.findAll();
		return new ResponseEntity<List<City>>(subCategories, HttpStatus.OK);
	}
	
	@RequestMapping(value="/cities/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<City>> getByIdCategorie(@PathVariable int id){
		List<City> City =  cityService.findAllByIdCountry(id);
		return new ResponseEntity<List<City>>(City, HttpStatus.OK);
	}
}
