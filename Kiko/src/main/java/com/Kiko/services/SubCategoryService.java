package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.SubCategory;
import com.Kiko.repositories.SubCategoryRepository;
@Service
public class SubCategoryService {
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	public SubCategoryService(SubCategoryRepository subCategoryRepository) {
		this.subCategoryRepository = subCategoryRepository;
	}

	public List<SubCategory> findAll() {
		return subCategoryRepository.findAll();
	}
	public List<SubCategory> findAllByIdCategorie(int idCategorie) {
		return  subCategoryRepository.findByIdCategorie(idCategorie);
	}
}
