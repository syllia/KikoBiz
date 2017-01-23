package com.investMessage.web.DTO;

import com.investMessage.domain.Customer;

public class CustomerDTO {
	public String number;
	public String name;
	public String store;
	public int numberBill;
	public String username;
	public String lastBillDate;
	public int id;

	public CustomerDTO(Customer customer) {
		this.number = customer.getNumber();
		this.name = customer.getName();
		this.store = customer.getStore().getName();
		this.numberBill = customer.getNumberBill();
		this.username = customer.getUser();
		this.id = customer.getId();
	}

	public CustomerDTO(String number, String name, String store, String username) {
		this.number = number;
		this.name = name;
		this.store = store;
		this.username = username;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getStore() {
		return store;
	}

	public int getNumberBill() {
		return numberBill;
	}

	public String getUsername() {
		return username;
	}

	public String getLastBillDate() {
		return lastBillDate;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof CustomerDTO) {
			CustomerDTO customerDTO = (CustomerDTO) object;
			return (this.number.equals(customerDTO.number) && this.name.equals(customerDTO.name));
		} else {
			return false;
		}
	}

}
