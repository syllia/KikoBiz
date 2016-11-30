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
		this.lastBillDate = customer.getLastBillDate().toString();
		this.id = customer.getId();
	}

	public CustomerDTO(String number, String name, String store, String username) {
		this.number = number;
		this.name = name;
		this.store = store;
		this.username = username;
	}

	public CustomerDTO(int id, String number, String name, String store, String username) {
		this.number = number;
		this.name = name;
		this.store = store;
		this.username = username;
		this.id = id;
	}

}
