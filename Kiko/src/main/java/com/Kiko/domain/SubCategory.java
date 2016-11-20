package com.Kiko.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSousCategorie;
	private String name;

	public SubCategory() {
	}

	public SubCategory(String name) {
		this.name = name;
	}

	public SubCategory(int id, String name) {
		this.name = name;
		this.idSousCategorie = id;
	}

	public int getIdSubCategory() {
		return idSousCategorie;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof SubCategory) {
			SubCategory subCategory = (SubCategory) object;
			return this.name == subCategory.name;
		} else {
			return false;
		}
	}

}
