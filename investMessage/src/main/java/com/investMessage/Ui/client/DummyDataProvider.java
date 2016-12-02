package com.investMessage.Ui.client;

import java.util.ArrayList;
import java.util.Collection;

import com.investMessage.Ui.DashboardNotification;
import com.investMessage.domain.FileDto;
import com.investMessage.services.CustomerIsAlreadyRegisteredException;
import com.investMessage.services.CustomerService;
import com.investMessage.services.DriveErrorException;
import com.investMessage.services.FileService;
import com.investMessage.services.StoreNotFoundException;
import com.investMessage.services.UserNotFoundException;
import com.investMessage.services.UserService;
import com.investMessage.web.DTO.CustomerDTO;
import com.investMessage.web.DTO.UserDTO;

public class DummyDataProvider implements DataProvider {

	private UserService userService;
	private CustomerService customerService;
	private FileService fileService;

	public DummyDataProvider(UserService userService, CustomerService customerService) {
		this.userService = userService;
		this.customerService = customerService;
		fileService = new FileService();
	}

	@Override
	public UserDTO authenticate(String userName, String password) {
		try {
			return userService.findUserBy(userName, password);
		} catch (UserNotFoundException e) {
			return null;
		}
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

	@Override
	public Collection<CustomerDTO> getRecentCustomer(String store) {
		Collection<CustomerDTO> collection = new ArrayList<>();
		try {
			collection = customerService.findCustomerByStore(store);
		} catch (CustomerIsAlreadyRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collection;
	}

	@Override
	public void saveUser(UserDTO user) throws UserNotFoundException, StoreNotFoundException {
		userService.updateInfos(user);
	}

	@Override
	public Collection<FileDto> getFiles(UserDTO user) {
		try {
			return fileService.findByUser(user);
		} catch (DriveErrorException e) {
			return new ArrayList<>();// TODO Auto-generated catch block

		}
	}

	@Override
	public void post(String title, String description, String filename) throws DriveErrorException {
		fileService.insertFile(title, description, filename);
		;

	}

}
