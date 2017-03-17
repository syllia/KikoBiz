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
public class SubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	@JsonProperty("idSousCategorie")
	private int idSousCategorie;
	@JsonProperty("idCategorie")
	private int idCategorie;
	@JsonProperty("name")
	private String name;
	
	public SubCategory() {
		// TODO Auto-generated constructor stub
	}

	public SubCategory(int idCategorie,String name) {
		this.name = name;
		this.idCategorie=idCategorie;
	}
	
	public int getIdSousCategorie() {
		return idSousCategorie;
	}


	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	
}
