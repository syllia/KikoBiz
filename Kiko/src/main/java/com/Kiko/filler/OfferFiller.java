package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;

import com.Kiko.repositories.OfferRepository;

public class OfferFiller {
	@Autowired
	public  OfferFiller(OfferRepository offerRepository) {
	}
}
