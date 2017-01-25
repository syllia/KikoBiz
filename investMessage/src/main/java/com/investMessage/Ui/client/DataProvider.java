package com.investMessage.Ui.client;

import java.util.Collection;
import java.util.List;

import com.investMessage.Ui.DashboardNotification;
import com.investMessage.services.CustomerIsAlreadyRegisteredException;
import com.investMessage.services.CustomerNotFoundException;
import com.investMessage.services.DocumentNotFoundException;
import com.investMessage.services.DriveErrorException;
import com.investMessage.web.DTO.CustomerDTO;
import com.investMessage.web.DTO.DocumentDTO;
import com.investMessage.web.DTO.StoreDTO;
import com.investMessage.web.DTO.UserDTO;

public interface DataProvider {

	UserDTO authenticate(String userName, String password);

	Collection<DocumentDTO> findDocumentByUser(UserDTO user);

	void saveDocument(DocumentDTO documentDTO) throws DriveErrorException;

	DocumentDTO findDocumentById(String documentId) throws DocumentNotFoundException;

	int getUnreadNotificationsCount();

	Collection<DashboardNotification> getNotifications();

	List<UserDTO> FindAllUsers();

	List<CustomerDTO> findAllCustomer();

	void saveClient(CustomerDTO customerDTO) throws CustomerIsAlreadyRegisteredException;

	Collection<StoreDTO> getStores();

	void updateClient(CustomerDTO customer) throws CustomerNotFoundException;

	void purchaseClient(CustomerDTO customer) throws CustomerNotFoundException;

	void deleteClient(CustomerDTO customer) throws CustomerNotFoundException;

}
