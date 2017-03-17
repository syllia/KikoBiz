package com.kouri.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Category")
public class Category {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	private String name;

	public Category() {
	}

	public Category(UUID uuid, String p_name) {
		this.id = uuid;
		this.name = p_name;
	}

	public Category(String p_name) {
		this.name = p_name;
	}

	public String getName() {
		return name;
	}

	public UUID getId() {
		return id;
	}
}
