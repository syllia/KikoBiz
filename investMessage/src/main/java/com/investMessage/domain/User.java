package com.investMessage.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	private String userName;
	private String lastname;
	private String firstname;
	private String passWord;
	@OneToOne
	private Store store;

	public User() {
	}

	public User(String userName, String firstName, String lastName, Store store, String passWord) {
		this.firstname = firstName;
		this.lastname = lastName;
		this.store = store;
		this.userName = userName;
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public Store getStore() {
		return store;
	}

	public String getLastname() {
		return lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getPassWord() {
		return passWord;
	}
}
