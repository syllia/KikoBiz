package com.Kiko.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.Kiko.model.ImageOffer;
import com.Kiko.services.ImageOfferService;
@RestController
public class ImageOfferController {
	@Autowired
	private ImageOfferService imageOfferService;

	@RequestMapping(value = "/imagesparoffre/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ImageOffer>> findAll(@PathVariable int id) {
		List<ImageOffer> images = imageOfferService.findByIdOffer(id);
		return new ResponseEntity<List<ImageOffer>>(images, HttpStatus.OK);
	}
}
