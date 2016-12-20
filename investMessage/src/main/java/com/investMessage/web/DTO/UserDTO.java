package com.investMessage.web.DTO;

import com.investMessage.domain.User;

public class UserDTO {
	public String userName;
	public String lastName;
	public String firstName;
	public String passWord;
	public String emailAddress;
	public String phoneNumber;

	public UserDTO(String userName, String lastname, String firstname, String passWord, String emailAddress,
			String phoneNumber) {
		this.userName = userName;
		this.lastName = lastname;
		this.firstName = firstname;
		this.passWord = passWord;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}

	public UserDTO(User user) {

		this.userName = user.getUserName();
		this.lastName = user.getLastname();
		this.firstName = user.getFirstname();
		this.passWord = user.getPassWord();
		this.emailAddress = user.getEmailAddress();
		this.phoneNumber = user.getPhoneNumber();
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