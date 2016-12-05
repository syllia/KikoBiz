package com.investMessage.Ui.client;

import java.util.Collection;

import com.investMessage.Ui.DashboardNotification;
import com.investMessage.Ui.window.FileDownloadFailure;
import com.investMessage.domain.FileDTO;
import com.investMessage.services.DriveErrorException;
import com.investMessage.services.StoreNotFoundException;
import com.investMessage.services.UserNotFoundException;
import com.investMessage.web.DTO.CustomerDTO;
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

	Collection<CustomerDTO> getRecentCustomer(String store);

	void saveUser(UserDTO user) throws UserNotFoundException, StoreNotFoundException;

	Collection<FileDTO> getFiles(UserDTO user);

	void post(String title, String description, String filename) throws DriveErrorException;

	byte[] getFileFromId(String fileId) throws FileDownloadFailure;

}
