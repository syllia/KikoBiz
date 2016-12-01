package com.investMessage.web.DTO;

import com.investMessage.domain.User;

public class UserDTO {
	public String userName;
	public String lastname;
	public String firstname;
	public String passWord;
	public String emailAddress;
	public String phoneNumber;
	public String store;

	public UserDTO(String userName, String lastname, String firstname, String passWord, String emailAddress,
			String phoneNumber, String store) {
		super();
		this.userName = userName;
		this.lastname = lastname;
		this.firstname = firstname;
		this.passWord = passWord;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.store = store;
	}

	public UserDTO(User user) {

		this.userName = user.getUserName();
		this.lastname = user.getLastname();
		this.firstname = user.getFirstname();
		this.passWord = user.getPassWord();
		this.emailAddress = user.getEmailAddress();
		this.phoneNumber = user.getPhoneNumber();
		this.store = user.getStore();
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
