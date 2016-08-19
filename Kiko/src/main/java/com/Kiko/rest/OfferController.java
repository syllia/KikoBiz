package com.Kiko.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kiko.model.Offer;
import com.Kiko.services.OfferService;
@RestController
public class OfferController {
@Autowired private OfferService offerService;

	
	@RequestMapping(value="/offres", method = RequestMethod.GET)
	public ResponseEntity<List<Offer>> findAll(){
		
		List<Offer> Offers =  offerService.findAll();
		return new ResponseEntity<List<Offer>>(Offers, HttpStatus.OK);
	}
	

	@RequestMapping(value="/offres/{id}", method = RequestMethod.GET)
	public ResponseEntity<Offer> getById(@PathVariable int id){
		
		Offer Offer =  offerService.findById(id);
		
		return new ResponseEntity<Offer>(Offer, HttpStatus.OK);
	}
	@RequestMapping(value="/offresbysouscategorie/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Offer>> getByIdSousCategorie(@PathVariable int id){
		
		List<Offer> Offer =  offerService.findByIdSousCategorieOrderByStartDateAsc(id);
		
		return new ResponseEntity<List<Offer>>(Offer, HttpStatus.OK);
	}

}
