package com.Kiko.domain;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SessionIdentifierGenerator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private int code;
	private String userId;

	public SessionIdentifierGenerator() {

	}

	public SessionIdentifierGenerator(String userId) {
		Random rNo = new Random();
		final int code = rNo.nextInt((99999 - 10000) + 1) + 10000;
		this.code = 12345;
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public int getCode() {
		return code;
	}

}
