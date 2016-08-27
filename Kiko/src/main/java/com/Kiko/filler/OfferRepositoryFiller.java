package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;

import com.Kiko.repositories.OfferRepository;

public class OfferRepositoryFiller {
	@Autowired
	public  OfferRepositoryFiller(OfferRepository offerRepository) {
	}
}
