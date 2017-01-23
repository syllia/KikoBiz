package com.investMessage.web.DTO;

import com.investMessage.domain.User;

public class UserDTO {
	public String userName;
	public String lastName;
	public String firstName;
	public String passWord;
	public String emailAddress;
	public String phoneNumber;
	public String type;

	public UserDTO(User user) {
		this.userName = user.getUserName();
		this.lastName = user.getLastname();
		this.firstName = user.getFirstname();
		this.passWord = user.getPassWord();
		this.emailAddress = user.getEmailAddress();
		this.phoneNumber = user.getPhoneNumber();
		this.type = user.getType();
	}

	public String getUserName() {
		return userName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
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
		if (object instanceof UserDTO) {
			UserDTO userDTO = (UserDTO) object;
			return (this.userName.equals(userDTO.userName));
		} else {
			return false;
		}
	}

}
