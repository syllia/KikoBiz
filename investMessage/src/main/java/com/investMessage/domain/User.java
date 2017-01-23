package com.investMessage.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String userName;
	private String lastname;
	private String firstname;
	private String passWord;
	private String emailAddress;
	private String phoneNumber;
	private String type;

	public User() {
	}

	public User(String userName, String lastname, String firstname, String passWord, String type) {
		super();
		this.userName = userName;
		this.lastname = lastname;
		this.firstname = firstname;
		this.passWord = passWord;
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public String getType() {
		return type;
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

	@Override
	public boolean equals(Object object) {
		if (object instanceof User) {
			User user = (User) object;
			return (this.userName.equals(user.userName) && this.emailAddress.equals(user.emailAddress)
					&& this.phoneNumber.equals(user.phoneNumber));
		} else {
			return false;
		}
	}

}
