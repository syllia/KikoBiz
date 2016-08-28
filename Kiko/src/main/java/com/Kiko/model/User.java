package com.Kiko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler","endDate" })
public class User {
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private int userId;
	public User(int userId){
		this.userId=userId;
	}
}
