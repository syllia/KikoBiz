package com.investMessage.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	private String userName;
	private String lastname;
	private String firstname;
	private String passWord;
	private String emailAddress;
	private String phoneNumber;
	@OneToMany
	private List<Document> documents;

	public User() {
	}

	public User(String userName, String lastname, String firstname, String passWord, String emailAddress,
			String phoneNumber, List<Document> documents) {
		super();
		this.userName = userName;
		this.lastname = lastname;
		this.firstname = firstname;
		this.passWord = passWord;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.documents = documents;
	}

	public String getUserName() {
		return userName;
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

	public List<Document> getDocuments() {
		return documents;
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
			return (this.userName.equals(user.userName) && this.emailAddress.equals(user.emailAddress)
					&& this.phoneNumber.equals(user.phoneNumber));
		} else {
			return false;
		}
	}

}
