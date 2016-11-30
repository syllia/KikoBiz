package com.kouri.OfferServer.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouri.OfferServer.domain.City;
import com.kouri.OfferServer.domain.CityAlreadyRegisteredException;
import com.kouri.OfferServer.domain.Country;
import com.kouri.OfferServer.domain.CountryRepository;

@Service
public class CountryService {
	private CountryRepository countryRepository;

	@Autowired
	public CountryService(CountryRepository CountryRepository) {
		this.countryRepository = CountryRepository;
	}

	public List<CountryDTO> findAll() {
		List<CountryDTO> countryDTOs = new ArrayList<CountryDTO>();
		for (Country countryRef : countryRepository.findAll()) {
			countryDTOs.add(new CountryDTO(countryRef));
		}
		return countryDTOs;
	}

	public void saveCountry(CountryDTO countryDTO) throws CountryAlreadyRegisteredException {
		if (countryRepository.findByName(countryDTO.name) == null) {
			countryRepository.save(new Country(countryDTO.name, countryDTO.phoneCode));
		} else {
			throw new CountryAlreadyRegisteredException();
		}
	}

	public void addCity(CityInputDTO cityInputDTO) throws CountryNotFoundException, CityAlreadyRegisteredException {

		Country country = countryRepository.findByName(cityInputDTO.countryName);
		if (country != null) {
			country.addCity(new City(cityInputDTO.cityName));
			countryRepository.save(country);

		} else {
			throw new CountryNotFoundException();
		}

	}

}
