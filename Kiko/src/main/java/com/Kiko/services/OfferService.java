package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.Offer;
import com.Kiko.repositories.OfferRepository;
@Service
public class OfferService {
	private OfferRepository offerRepository;

	@Autowired
	public OfferService(OfferRepository offerRepository) {
		this.offerRepository = offerRepository;
	}

	public List<Offer> findAll() {
		return offerRepository.findByOrderByStartDateAsc();
	}

	public Offer findById(int id) {
		return offerRepository.getOne(id);
	}
	public List<Offer> findByIdSousCategorieOrderByStartDateAsc(int id) {
		return offerRepository.findByIdSousCategorieOrderByStartDateAsc(id);
	}
	public Offer save(Offer offer) {
		return offerRepository.saveAndFlush(offer);
	}

}
