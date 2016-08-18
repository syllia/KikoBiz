package com.Kiko.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kiko.model.Offre;
import com.Kiko.services.OfferService;
@RestController
public class OfferRest {
@Autowired private OfferService offerService;

	
	@RequestMapping(value="/offres", method = RequestMethod.GET)
	public ResponseEntity<List<Offre>> findAll(){
		
		List<Offre> Offres =  offerService.findAll();
		return new ResponseEntity<List<Offre>>(Offres, HttpStatus.OK);
	}
	

	@RequestMapping(value="/offres/{id}", method = RequestMethod.GET)
	public ResponseEntity<Offre> getById(@PathVariable int id){
		
		Offre Offre =  offerService.findById(id);
		
		return new ResponseEntity<Offre>(Offre, HttpStatus.OK);
	}
	@RequestMapping(value="/offresbysouscategorie/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Offre>> getByIdSousCategorie(@PathVariable int id){
		
		List<Offre> Offre =  offerService.findByIdSousCategorieOrderByStartDateAsc(id);
		
		return new ResponseEntity<List<Offre>>(Offre, HttpStatus.OK);
	}

}
