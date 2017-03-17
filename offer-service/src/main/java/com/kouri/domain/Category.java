package com.kouri.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Category")
public class Category {

	@Id
	private String id;
	private String name;

	public Category() {
	}

	public Category(String p_name) {
		this.id = UUID.randomUUID().toString();
		this.name = p_name;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
}
