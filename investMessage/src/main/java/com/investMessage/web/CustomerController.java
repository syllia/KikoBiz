package com.investMessage.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.investMessage.model.Customer;
import com.investMessage.model.MessageDefine;
import com.investMessage.repositories.CustomerRepository;
import com.investMessage.repositories.StoreRepository;
import com.investMessage.services.ClickatellServices;
import com.investMessage.web.DTO.CustomerDTO;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
	@Autowired
	private StoreRepository stores;
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	private CustomerRepository repository;

	@Autowired
	public CustomerController(CustomerRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = "/bystores/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<CustomerDTO>> customersByStores(@PathVariable String id) {
		List<CustomerDTO> customers = repository.findByShop(id).stream().map(customer -> {
			CustomerDTO customerDTO = new CustomerDTO(customer);
			return customerDTO;
		}).collect(Collectors.toList());

		return new ResponseEntity<>(customers, HttpStatus.OK);

	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<CustomerDTO>> customers() {
		List<CustomerDTO> customers = repository.findAll().stream().map(customer -> {
			CustomerDTO customerDTO = new CustomerDTO(customer);
			return customerDTO;
		}).collect(Collectors.toList());

		return new ResponseEntity<>(customers, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> updateDate(@PathVariable String id) {
		if (repository.exists(id)) {

			Customer customer = repository.findOne(id);
			customer.setLastBillDate(LocalDateTime.now());
			customer = repository.save(customer);
			CustomerDTO customerDTO = new CustomerDTO(customer);
			log.info("Notification achat :: " + customer.toString());
			return new ResponseEntity<>(customerDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);

	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> post(@RequestBody @Valid CustomerDTO customerDTO)
			throws IOException, InterruptedException {
		if (!stores.findByStore(customerDTO.store).isEmpty()) {
			if (customerDTO.number != null) {
				if (repository.findByNumber(customerDTO.number).isEmpty()) {
					Customer customer = new Customer(customerDTO.number, customerDTO.name, customerDTO.store);
					customer = repository.save(customer);
					log.info("Ajout client :: " + customer.toString());
					ClickatellServices.sendMessage(MessageDefine.welcome, customer.getNumber());
					return new ResponseEntity<>(new CustomerDTO(customer), HttpStatus.CREATED);
				}
				return new ResponseEntity<>(customerDTO, HttpStatus.ALREADY_REPORTED);
			}
			return new ResponseEntity<>(customerDTO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Store", HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getById(@PathVariable String id) {
		Customer customer = repository.findOne(id);
		if (customer != null) {
			CustomerDTO customerDTO = new CustomerDTO(customer);
			return new ResponseEntity<>(customerDTO, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteById(@PathVariable String id) {
		Customer customer = repository.findOne(id);
		if (customer != null) {
			repository.delete(id);
			log.info("Suppression client :: " + customer.toString());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
