package com.investMessage.Ui;

import java.io.Serializable;

import com.investMessage.domain.User;
import com.vaadin.spring.annotation.UIScope;

@UIScope
public class UserInfo implements Serializable {
	private User user;

	public UserInfo() {
		this.user = null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}