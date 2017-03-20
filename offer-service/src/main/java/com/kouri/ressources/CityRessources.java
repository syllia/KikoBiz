package com.kouri.ressources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kouri.domain.City;
import com.kouri.services.CityService;
import com.kouri.services.ElementIsAlreadyAddedExecption;

public class CityRessources {
	@Autowired
	private CityService cityService;

	@RequestMapping("/cities")
	public ResponseEntity<List<City>> getAllCategories() {
		List<City> cities = cityService.findAll();
		return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	}

	@RequestMapping(value = "/cities", method = RequestMethod.POST)
	public ResponseEntity<?> addNewcities(@RequestBody City cityParam) {
		City city;
		try {
			city = cityService.save(cityParam.getNameCity());
			return new ResponseEntity<>(city, HttpStatus.OK);
		} catch (ElementIsAlreadyAddedExecption e) {
			return new ResponseEntity<>("City already exist", HttpStatus.BAD_REQUEST);
		}

	}
}
