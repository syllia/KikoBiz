package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.SousCategorie;
import com.Kiko.repositories.SousCategorieRepository;
@Service
public class SousCategorieService {
	private SousCategorieRepository sousCategorieRepository;

	@Autowired
	public SousCategorieService(SousCategorieRepository sousCategorieRepository) {
		this.sousCategorieRepository = sousCategorieRepository;
	}

	public List<SousCategorie> findAll() {
		return sousCategorieRepository.findAll();
	}

	public SousCategorie findById(int id) {
		return sousCategorieRepository.getOne(id);
	}
	/*public SousCategorie findByIdCategorie(int idCategorie) {
		return sousCategorieRepository.findByidCategorie();
	}*/
}
