package com.investMessage.Ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.investMessage.services.UserService;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {
	private UserService userService;
	private UserInfo userInfo;

	@Autowired
	public VaadinUI(UserService repo, UserInfo userInfo) {
		this.userService = repo;
		this.userInfo = userInfo;
	}

	@Override
	protected void init(VaadinRequest request) {
		Navigator navigator = new Navigator(this, this);
		navigator.addView("login", new LoginView(userService, userInfo));
		navigator.navigateTo("login");
	}
}
