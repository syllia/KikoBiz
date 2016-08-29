package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.City;
import com.Kiko.repositories.CityRepository;
@Service
public class CityService {
	private CityRepository cityRepository;

	@Autowired
	public CityService(CityRepository CityRepository) {
		this.cityRepository = CityRepository;
	}
	
	public List<City> findAll() {
		return cityRepository.findAll();
	}

	public List<City> findAllByIdCountry(int id) {
		return cityRepository.findByIdCountry(id);	}

}
