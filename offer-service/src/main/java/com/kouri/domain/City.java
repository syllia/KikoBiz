package com.kouri.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class City {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID idCity;
	private Country country;
	private String nameCity;

	public City() {
	}

	public City(Country country, String nameCity) {
		this.nameCity = nameCity;
		this.country = country;
	}

	public UUID getIdCity() {
		return idCity;
	}

	public Country getCountry() {
		return country;
	}

	public String getNameCity() {
		return nameCity;
	}

}
