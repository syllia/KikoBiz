package com.investMessage.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "customer")
public class Customer {
	@Id
	@Column(length = 40)
	@GeneratedValue(generator = "randomId")
	@GenericGenerator(name = "randomId", strategy = "com.investMessage.model.RandomIdGenerator")
	private String id;
	@Column(length = 1024)
	private String number;
	@Column(length = 1024)
	private String name;
	@Column(length = 1024)
	private String shop;
	private int numberBill;
	@Column(length = 1024)
	private String lastBillString;
	@Column(length = 1024)
	private String info;
	private LocalDateTime lastBillDate;

	public Customer() {

	}

	public Customer(String number, String name, String shop) {
		this.numberBill = 0;
		this.number = number;
		this.name = name;
		this.shop = shop;
		this.lastBillString = "Pas d'achat";
		this.info = "Nouveau client enrégistré";
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
		this.setInfo("Ce client a acheté cette semaine");

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getNumberBill() {
		return numberBill;
	}

	public void setNumberBill(int numberBill) {
		this.numberBill = numberBill;
	}

	public String toString() {
		if (lastBillDate != null) {
			return "Client :" + this.name + " " + this.number + " " + this.shop + " " + this.lastBillDate.getDayOfWeek()
					+ "-" + this.lastBillDate.getDayOfMonth() + "-" + this.lastBillDate.getMonth().toString();
		}
		return "Client :" + this.name + " " + this.number + " " + this.shop;
	}

}
