package com.investMessage.Ui;

import java.util.Collection;

import com.investMessage.domain.User;
import com.investMessage.services.UserService;

public class DummyDataProvider implements DataProvider {
	;
	private UserService userService;

	public DummyDataProvider(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User authenticate(String userName, String password) {
		return userService.getUserBy(userName, password);
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
