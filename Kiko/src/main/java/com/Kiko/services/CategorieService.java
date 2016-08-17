package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.Categorie;
import com.Kiko.repositories.CategorieRepository;
@Service
public class CategorieService {
	private CategorieRepository categorieRepository;
	@Autowired 
	public CategorieService(CategorieRepository CategorieRepository){
		this.categorieRepository = CategorieRepository;
	}
	
	public List<Categorie> findAll() {
		return categorieRepository.findAll();
	}

	public Categorie findById(int id) {
		return categorieRepository.getOne(id);
	}
}
