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
	private String emailAddress;
	private String phoneNumber;
	@OneToOne
	private Store store;

	public User() {
	}

	public User(String userName, String firstName, String lastName, Store store, String passWord, String emailAddress,
			String phoneNumber) {
		this.firstname = firstName;
		this.lastname = lastName;
		this.store = store;
		this.userName = userName;
		this.passWord = passWord;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public String getStore() {
		return store.getName();
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void update(String password, String phoneNumber, String email) {
		this.passWord = password;
		this.phoneNumber = phoneNumber;
		this.emailAddress = email;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof User) {
			User user = (User) object;
			return (this.userName.equals(user.userName) && this.passWord.equals(user.passWord)
					&& this.emailAddress.equals(user.emailAddress) && this.phoneNumber.equals(user.phoneNumber));
		} else {
			return false;
		}
	}

}
