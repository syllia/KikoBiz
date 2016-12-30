package com.investMessage.web.DTO;

import java.util.List;

import com.investMessage.domain.Store;
import com.investMessage.domain.User;

public class UserDTO {
	public String userName;
	public String lastName;
	public String firstName;
	public String passWord;
	public String emailAddress;
	public String phoneNumber;
	public List<String> stores;

	public UserDTO(User user) {
		this.userName = user.getUserName();
		this.lastName = user.getLastname();
		this.firstName = user.getFirstname();
		this.passWord = user.getPassWord();
		this.emailAddress = user.getEmailAddress();
		this.phoneNumber = user.getPhoneNumber();
		for (Store store : user.getStores()) {
			this.stores.add(store.getName());
		}
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
