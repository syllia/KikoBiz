package com.investMessage.web.DTO;

import com.investMessage.model.Customer;

public class CustomerDTO {
	public Long id;
	public String number;
	public String name;
	public String store;
	public int numberBill;
	public String LastBilDate;
	public String info;

	public CustomerDTO() {

	}

	public CustomerDTO(String number, String name, String store) {
		this.number = number;
		this.name = name;
		this.store = store;
	}

	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.number = customer.getNumero();
		this.name = customer.getName();
		this.store = customer.getShop();
		this.numberBill = customer.getNumberBill();
		this.LastBilDate = customer.getLastBillString();
		this.info = customer.getInfo();
	}

}
