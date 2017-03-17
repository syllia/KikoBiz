package com.kouri.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class SubCategory {
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@GenericGenerator(name = "hibernate-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID idSubCategory;
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;

	private String name;

	public SubCategory() {
	}

	public SubCategory(Category category, String name) {
		this.name = name;
		this.category = category;
	}

	public UUID getIdSubCategory() {
		return idSubCategory;
	}

	public Category getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}
}