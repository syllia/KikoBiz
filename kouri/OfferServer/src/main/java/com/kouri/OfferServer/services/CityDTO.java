package com.kouri.OfferServer.services;

import com.kouri.OfferServer.domain.City;

public class CityDTO {
	public String name;

	public CityDTO(City city) {
		this.name = city.getName();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof CityDTO) {
			CityDTO cityDTO = (CityDTO) object;
			return (this.name.equals(cityDTO.name));
		} else {
			return false;
		}
	}
}