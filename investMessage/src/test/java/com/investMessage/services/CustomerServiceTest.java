package com.investMessage.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.investMessage.domain.Customer;
import com.investMessage.domain.CustomerRepository;
import com.investMessage.domain.Store;
import com.investMessage.web.DTO.CustomerDTO;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	@Mock
	CustomerRepository customerRepository;
	@InjectMocks
	CustomerService customerService = new CustomerService(customerRepository);

	CustomerDTO customerDTO;
	Customer customer;
	Customer updatedCustomer;

	@Before
	public void setUp() {
		customer = new Customer("userName", "+229", "CustomerName", new Store("store"));
		updatedCustomer = new Customer("userName", "+239", "CustomerName1", new Store("store1"));
		customerDTO = new CustomerDTO(updatedCustomer);
	}

	@Test(expected = CustomerNotFoundException.class)
	public void aCustomerService_updateCustomer_ThrowsNotFoundException() throws CustomerNotFoundException {
		Mockito.when(customerRepository.findOne(customerDTO.id)).thenReturn(null);
		customerService.updateCustomer(customerDTO);

	}

	@Test
	public void aCustomerService_updateCustomer_CustomerIsNotDeleted() throws CustomerNotFoundException {
		Mockito.when(customerRepository.getOne(customerDTO.id)).thenReturn(customer);
		customerService.updateCustomer(customerDTO);
		assertNotNull(customerRepository.getOne(customerDTO.id));
	}

	@Test
	public void aCustomerService_updateCustomer_CustomerIsUpdated() throws CustomerNotFoundException {
		Mockito.when(customerRepository.getOne(customerDTO.id)).thenReturn(customer);
		customerService.updateCustomer(customerDTO);
		assertEquals(customerRepository.getOne(customerDTO.id), updatedCustomer);
	}

}
