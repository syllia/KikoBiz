package com.Kiko.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kiko.model.Country;
import com.Kiko.services.CountryService;
@RestController
public class CountryController {
	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<List<Country>> findAll() {

		List<Country> countries = countryService.findAll();
		return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
	}
}
