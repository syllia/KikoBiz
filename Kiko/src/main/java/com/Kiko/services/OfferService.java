package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.Offre;
import com.Kiko.repositories.OfferRepository;
@Service
public class OfferService {
	private OfferRepository offerRepository;

	@Autowired
	public OfferService(OfferRepository offerRepository) {
		this.offerRepository = offerRepository;
	}

	public List<Offre> findAll() {
		return offerRepository.findByOrderByStartDateAsc();
	}

	public Offre findById(int id) {
		return offerRepository.getOne(id);
	}
	public List<Offre> findByIdSousCategorieOrderByStartDateAsc(int id) {
		return offerRepository.findByIdSousCategorieOrderByStartDateAsc(id);
	}
	public Offre save(Offre offre) {
		return offerRepository.saveAndFlush(offre);
	}

}
