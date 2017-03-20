package com.kouri.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String userId;

	public User() {
	}

	public User(String userId) {
		this.userId = userId;
	}
}
