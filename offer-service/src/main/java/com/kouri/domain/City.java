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

	private String name;

	public City() {
	}

	public City(String nameCity) {
		this.name = nameCity;
	}

	public UUID getIdCity() {
		return idCity;
	}

	public String getNameCity() {
		return name;
	}

}
