package com.investMessage.Ui;

import java.util.Collection;

import com.investMessage.domain.User;

public interface DataProvider {

	User authenticate(String userName, String password);

	/**
	 * @return The number of unread notifications for the current user.
	 */
	int getUnreadNotificationsCount();

	/**
	 * @return Notifications for the current user.
	 */
	Collection<DashboardNotification> getNotifications();
}
