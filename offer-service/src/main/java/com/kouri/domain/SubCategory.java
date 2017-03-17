package com.Kiko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import Category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class SubCategory {
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "hibernate-uuid")
	@Column(name = "uuid", unique = true)
	@JsonProperty("idSubCategory")
	private UUID idSubCategory;

	@JsonProperty("idCategory")
	private Category idCategory;

	@JsonProperty("name")
	private String name;
	
	public SubCategory() {
	}

	public SubCategory(Category idCategory,String name) {
		this.name = name;
		this.idCategory = idCategory;
	}
	
	public int getIdSubCategory() {
		return idSubCategory;
	}


	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Category idCategory) {
		this.idCategory = idCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}