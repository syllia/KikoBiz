package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.Country;
import com.Kiko.repositories.CountryRepository;

@Service
public class CountryService {
	private CountryRepository countryRepository;
	@Autowired 
	public CountryService(CountryRepository countryRepository){
		this.countryRepository = countryRepository;
	}
	
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	public Country findById(int id) {
		return countryRepository.getOne(id);
	}
}
