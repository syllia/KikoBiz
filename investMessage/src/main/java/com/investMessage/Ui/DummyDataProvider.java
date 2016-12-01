package com.investMessage.Ui;

import java.util.Collection;

import com.investMessage.services.UserService;
import com.investMessage.web.DTO.UserDTO;

public class DummyDataProvider implements DataProvider {
	;
	private UserService userService;

	public DummyDataProvider(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDTO authenticate(String userName, String password) {
		return userService.findUserBy(userName, password);
	}

	@Override
	public int getUnreadNotificationsCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Collection<DashboardNotification> getNotifications() {
		// TODO Auto-generated method stub
		return null;
	}

}
