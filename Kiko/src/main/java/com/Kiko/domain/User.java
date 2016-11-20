package com.Kiko.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String phone;

	public User() {
	}

	public User(String phone) {
		this.phone = phone;
	}
}
