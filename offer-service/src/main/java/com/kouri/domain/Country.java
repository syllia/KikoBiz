package com.kouri.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Country {
	@Id
	private String code;
	private String name;

	public Country() {
	}

	public Country(String code, String name) {
		this.code = code;
		this.name = name;

	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
