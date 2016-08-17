package com.Kiko.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SousCategorie {
	@Id
	private int idSousCategorie;
	private int idCategorie;
	private String name;
	
	public SousCategorie() {
		// TODO Auto-generated constructor stub
	}

	public SousCategorie(int idSousCategorie,int idCategorie,String name) {
		this.name = name;
		this.idSousCategorie=idSousCategorie;
		this.idCategorie=idCategorie;
	}
	
	public int getIdSousCategorie() {
		return idSousCategorie;
	}

	public void setIdSousCategorie(int idSousCategorie) {
		this.idSousCategorie = idSousCategorie;
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
