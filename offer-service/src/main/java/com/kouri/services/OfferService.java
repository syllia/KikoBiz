package com.kouri.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouri.domain.CityRepository;
import com.kouri.domain.Offer;
import com.kouri.domain.OfferRepository;
import com.kouri.domain.SubCategoryRepository;
import com.kouri.domain.UserRepository;

@Service
public class OfferService {
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private UserRepository userRepository;

	public Offer save(OfferDto offerDto) throws ElementNotExistException {
		if (subCategoryRepository.exists(UUID.fromString(offerDto.subCategoryId))) {
			if (cityRepository.exists(UUID.fromString(offerDto.cityId))) {
				if (cityRepository.exists(UUID.fromString(offerDto.userId))) {
					Offer offer = new Offer(userRepository.getOne(offerDto.userId),
							cityRepository.getOne(UUID.fromString(offerDto.cityId)), offerDto.name, offerDto.cost,
							offerDto.description, subCategoryRepository.getOne(UUID.fromString(offerDto.subCategoryId)),
							offerDto.contact);
					offerRepository.save(offer);
					postPhoto(offerDto.photos, offer.getOfferId());
				}
			}
		}
		throw new ElementNotExistException();
	}

	private void postPhoto(List<byte[]> photos, UUID offer) {

	}
}
