package com.Kiko.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class SessionIdentifierGenerator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private int code;
	private String userId;
	public SessionIdentifierGenerator(){
		
	}
	public SessionIdentifierGenerator(String userId){
		Random rNo = new Random();
		final int code = rNo.nextInt((99999 - 10000) + 1) + 10000;
		this.code=12345;
		this.userId=userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

}
