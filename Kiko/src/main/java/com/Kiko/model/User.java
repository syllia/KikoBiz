package com.Kiko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class User {
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@JsonProperty("user")
	private int userId;
	public User (){}
	public User(int userId){
		this.userId=userId;
	}
}
