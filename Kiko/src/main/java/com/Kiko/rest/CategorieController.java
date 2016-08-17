package com.Kiko.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kiko.model.Categorie;
import com.Kiko.services.CategorieService;

@RestController
public class CategorieController {
	@Autowired
	private CategorieService categorieService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<List<Categorie>> findAll() {

		List<Categorie> categories = categorieService.findAll();
		return new ResponseEntity<List<Categorie>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categorie> getById(@PathVariable int id) {

		Categorie categorie = categorieService.findById(id);

		return new ResponseEntity<Categorie>(categorie, HttpStatus.OK);
	}
}
