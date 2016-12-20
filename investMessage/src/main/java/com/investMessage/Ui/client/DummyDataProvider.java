package com.investMessage.Ui.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.investMessage.Ui.DashboardNotification;
import com.investMessage.services.CustomerService;
import com.investMessage.services.DocumentNotFoundException;
import com.investMessage.services.DocumentService;
import com.investMessage.services.DriveErrorException;
import com.investMessage.services.UserNotFoundException;
import com.investMessage.services.UserService;
import com.investMessage.web.DTO.DocumentDTO;
import com.investMessage.web.DTO.UserDTO;

public class DummyDataProvider implements DataProvider {

	private UserService userService;
	private CustomerService customerService;
	private DocumentService documentService;

	public DummyDataProvider() {
		this.userService = new UserService();
		this.customerService = new CustomerService();
		this.documentService = new DocumentService();
	}

	public DummyDataProvider(UserService userService, CustomerService customerService,
			DocumentService documentService) {
		this.userService = userService;
		this.customerService = customerService;
		this.documentService = documentService;
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
		return userService.findDocumentsByUser(user);
	}

	@Override
	public void saveDocument(DocumentDTO documentDTO) throws DriveErrorException {
		documentService.saveDocument(documentDTO);

	}

	@Override
	public DocumentDTO findDocumentById(String documentId) throws DocumentNotFoundException {
		return documentService.findDocumentById(documentId);
	}

	@Override
	public List<UserDTO> FindAllUsers() {
		// TODO Auto-generated method stub
		return new ArrayList<UserDTO>();
	}

}
