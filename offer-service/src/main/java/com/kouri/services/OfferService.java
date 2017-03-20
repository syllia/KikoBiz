package com.kouri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouri.domain.CityRepository;
import com.kouri.domain.Offer;
import com.kouri.domain.OfferRepository;
import com.kouri.domain.SubCategoryRepository;

@Service
public class OfferService {
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	public Offer save(OfferDto offerDto) {
		// UUID uidSubCategory = UUID.fromString(offerDto.s);

	}
}
