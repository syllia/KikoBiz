package com.investMessage.Ui;

import java.util.Collection;

import com.investMessage.web.DTO.UserDTO;

public interface DataProvider {

	UserDTO authenticate(String userName, String password);

	/**
	 * @return The number of unread notifications for the current user.
	 */
	int getUnreadNotificationsCount();

	/**
	 * @return Notifications for the current user.
	 */
	Collection<DashboardNotification> getNotifications();
}
