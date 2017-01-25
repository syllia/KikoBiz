package com.investMessage.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	private static final int DEFAULT_NUMBER_BILL = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String registeredBy;
	private String number;
	private String name;
	@OneToOne(cascade = CascadeType.DETACH)
	private Store store;
	private int numberBill;
	private LocalDateTime lastBillDate;

	public Customer(String registedBy, String number, String name, Store store) {
		this.number = number;
		this.name = name;
		this.store = store;
		this.numberBill = DEFAULT_NUMBER_BILL;
		this.registeredBy = registedBy;

	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public String getUser() {
		return registeredBy;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public Store getStore() {
		return store;
	}

	public int getNumberBill() {
		return numberBill;
	}

	public LocalDateTime getLastBillDate() {
		return lastBillDate;
	}

	public void update(String number, String name) {
		this.name = name;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void updatePurchase() {
		this.lastBillDate = LocalDateTime.now();
		this.numberBill++;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Customer) {
			Customer customer = (Customer) object;
			return (this.number.equals(customer.number));
		} else {
			return false;
		}
	}

}
