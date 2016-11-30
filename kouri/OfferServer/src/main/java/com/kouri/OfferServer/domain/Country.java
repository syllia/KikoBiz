package com.kouri.OfferServer.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Country {
	@Id
	private String phoneCode;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<City> cities;

	public Country(String name, String code) {
		this.phoneCode = code;
		this.name = name;
		this.cities = new ArrayList<City>();

	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public String getName() {
		return name;
	}

	public List<City> getCities() {
		return cities;
	}

	public void addCity(City city) throws CityAlreadyRegisteredException {
		if (!hasCity(city)) {
			cities.add(city);
		} else {
			throw new CityAlreadyRegisteredException();
		}

	}

	private boolean hasCity(City city) {
		for (City cityRef : cities) {
			if (city.equals(cityRef)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Country) {
			Country country = (Country) object;
			return (this.name.equals(country.name));
		} else {
			return false;
		}
	}
}
