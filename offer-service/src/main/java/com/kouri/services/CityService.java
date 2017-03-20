package com.kouri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouri.domain.City;
import com.kouri.domain.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;

	public CityService(CityRepository CityRepository) {
		this.cityRepository = CityRepository;
	}

	public List<City> findAll() {
		return cityRepository.findAll();
	}

	public City save(String name) throws ElementIsAlreadyAddedExecption {
		List<City> listOfCategory = cityRepository.findByName(name);
		if (listOfCategory.isEmpty()) {
			return cityRepository.save(new City(name));
		}
		throw new ElementIsAlreadyAddedExecption();
	}

}
