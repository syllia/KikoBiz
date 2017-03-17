package com.kouri.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

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

	public SubCategory(Category idCategory, String name) {
		this.name = name;
		this.idCategory = idCategory;
	}

	public UUID getIdSubCategory() {
		return idSubCategory;
	}

	public Category getCategory() {
		return idCategory;
	}

	public String getName() {
		return name;
	}
}