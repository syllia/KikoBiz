package com.Kiko.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCity;
	private String name;

	public City() {
	}

	public City(String name) {
		this.name = name;
	}

	public int getIdCity() {
		return idCity;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof City) {
			City city = (City) object;
			return this.name == city.name;
		} else {
			return false;
		}
	}

}
