package com.Kiko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Country {
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@JsonProperty("code")
	int code;
	@JsonProperty("name")
	private String name;

	public Country() {

	}

	public Country(int code, String name) {
		this.code=code;
		this.name=name;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
