package com.investMessage.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "customer")
public class Customer {
	@Id
	// @Column(length = 40)
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @GeneratedValue(generator = "randomId")
	// @GenericGenerator(name = "randomId", strategy =
	// "com.investMessage.model.RandomIdGenerator")
	private Long id;
	@Column(length = 1024)
	private String numero;
	@Column(length = 1024)
	private String name;
	@Column(length = 1024)
	private String shop;
	private int numberBill;
	@Column(length = 1024)
	private String lastBillString;
	private LocalDateTime lastBillDate;

	public Customer() {

	}

	public Customer(String number, String name, String shop) {
		this.numero = number;
		this.name = name;
		this.shop = shop;
		this.numberBill = 0;
		this.lastBillString = "Pas d'achat";
	}

	public String getLastBillString() {
		return lastBillString;
	}

	public void setLastBillString() {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		String date = DATE_FORMAT.format(new Date());
		this.lastBillString = date;
	}

	public LocalDateTime getLastBillDate() {
		return lastBillDate;
	}

	public void setLastBillDate(LocalDateTime lastBillDate) {
		this.numberBill = numberBill + 1;
		this.lastBillDate = lastBillDate;
		setLastBillString();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String idShop) {
		this.shop = idShop;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumber(String number) {
		this.numero = number;
	}

	public int getNumberBill() {
		return numberBill;
	}

	public void setNumberBill(int numberBill) {
		this.numberBill = numberBill;
	}

	public String toString() {
		if (lastBillDate != null) {
			return "Client :" + this.name + " " + this.numero + " " + this.shop + " " + this.lastBillDate.getDayOfWeek()
					+ "-" + this.lastBillDate.getDayOfMonth() + "-" + this.lastBillDate.getMonth().toString();
		}
		return "Client :" + this.name + " " + this.numero + " " + this.shop;
	}

}
