package com.investMessage.Ui.client;

import java.util.Collection;
import java.util.List;

import com.investMessage.Ui.DashboardNotification;
import com.investMessage.services.CustomerIsAlreadyRegisteredException;
import com.investMessage.services.CustomerNotFoundException;
import com.investMessage.services.CustomerService;
import com.investMessage.services.DocumentNotFoundException;
import com.investMessage.services.DriveErrorException;
import com.investMessage.services.UserNotFoundException;
import com.investMessage.services.UserService;
import com.investMessage.web.DTO.CustomerDTO;
import com.investMessage.web.DTO.DocumentDTO;
import com.investMessage.web.DTO.StoreDTO;
import com.investMessage.web.DTO.UserDTO;

public class DummyDataProvider implements DataProvider {

	private UserService userService;
	private CustomerService customerService;

	public DummyDataProvider() {
		this.userService = new UserService();
		this.customerService = new CustomerService();

	}

	public DummyDataProvider(UserService userService, CustomerService customerService) {
		this.userService = userService;
		this.customerService = customerService;

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
	public Collection<DocumentDTO> findDocumentByUser(UserDTO user) {
		return null;
	}

	@Override
	public List<UserDTO> FindAllUsers() {
		return userService.findAll();
	}

	@Override
	public List<CustomerDTO> findAllCustomer() {
		return customerService.findAll();
	}

	@Override
	public void saveClient(CustomerDTO customerDTO) throws CustomerIsAlreadyRegisteredException {
		customerService.saveCustomer(customerDTO);

	}

	@Override
	public void saveDocument(DocumentDTO documentDTO) throws DriveErrorException {
		// TODO Auto-generated method stub

	}

	@Override
	public DocumentDTO findDocumentById(String documentId) throws DocumentNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<StoreDTO> getStores() {
		return customerService.getStores();

	}

	@Override
	public void updateClient(CustomerDTO customerDTO) throws CustomerNotFoundException {
		customerService.updateCustomer(customerDTO);

	}

	@Override
	public void purchaseClient(CustomerDTO customer) throws CustomerNotFoundException {

		customerService.updateOrder(customer.id);

	}

	@Override
	public void deleteClient(CustomerDTO customer) throws CustomerNotFoundException {
		customerService.deleteCustomer(customer.id);

	}

}
