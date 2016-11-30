package com.kouri.OfferServer.services;

import java.util.ArrayList;
import java.util.List;

import com.kouri.OfferServer.domain.City;
import com.kouri.OfferServer.domain.Country;

public class CountryDTO {
	public String phoneCode;
	public String name;
	public List<CityDTO> cityDTOs = new ArrayList<CityDTO>();

	public CountryDTO(Country country) {
		this.name = country.getName();
		addCityDTO(country.getCities());
		this.phoneCode = country.getPhoneCode();
	}

	public CountryDTO(String name, String phoneCode) {
		this.name = name;
		this.phoneCode = phoneCode;
	}

	private void addCityDTO(List<City> cities) {
		for (City cityRef : cities) {
			this.cityDTOs.add(new CityDTO(cityRef));
		}
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof CountryDTO) {
			CountryDTO countryDTO = (CountryDTO) object;
			return (this.name.equals(countryDTO.name));
		} else {
			return false;
		}
	}

}
