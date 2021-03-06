package com.investMessage.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investMessage.domain.Customer;
import com.investMessage.domain.CustomerRepository;
import com.investMessage.domain.Store;
import com.investMessage.domain.User;
import com.investMessage.domain.UserRepository;
import com.investMessage.web.DTO.CustomerDTO;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private UserRepository userRepository;

	public CustomerService() {
	}

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void saveCustomer(CustomerDTO customerDTO) throws CustomerIsAlreadyRegisteredException {
		if (customerRepository.findByNumberAndStore(customerDTO.number, customerDTO.store) == null) {
			customerRepository.save(new Customer(customerDTO.username, customerDTO.number, customerDTO.name,
					new Store(customerDTO.store)));
		} else {
			throw new CustomerIsAlreadyRegisteredException();
		}
	}

	public void updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException {
		Customer customer = customerRepository.getOne(customerDTO.id);
		if (customer != null) {
			customer.update(customerDTO.number, customer.getName());
			customerRepository.save(customer);
		} else {
			throw new CustomerNotFoundException();
		}
	}

	public List<CustomerDTO> findCustomerByUser(String username) {
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		User user = userRepository.findOne(username);
		// for (Store store : ) {
		// for (Customer customer :
		// customerRepository.findByStoreOrderByNameAsc(store.getName())) {
		// customerDTOs.add(new CustomerDTO(customer));
		// }
		// }

		return customerDTOs;
	}

	public void deleteCustomer(int id) throws CustomerNotFoundException {
		Customer customer = customerRepository.getOne(id);
		if (customer != null) {
			customerRepository.delete(customer);
		} else {
			throw new CustomerNotFoundException();
		}
	}

	public void updateOrder(int id) throws CustomerNotFoundException {
		Customer customer = customerRepository.getOne(id);
		if (customer != null) {
			customer.updatePurchase();
			customerRepository.save(customer);
		} else {
			throw new CustomerNotFoundException();
		}
	}

}
