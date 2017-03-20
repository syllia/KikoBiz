package com.kouri.domain;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Offer {
	public static long limitMonth = 6;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	@OneToOne(cascade = CascadeType.ALL)
	private SubCategory subCategory;
	private String nameOffer;
	private String contact;
	private long nbViews;
	private long cost;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;
	@OneToOne(cascade = CascadeType.ALL)
	private City city;

	public Offer() {
	}

	public Offer(User user, City city, String p_name, long cost, String description, SubCategory subCategory,
			String contact) {
		this.user = user;
		this.subCategory = subCategory;
		this.nameOffer = p_name;
		this.contact = contact;
		this.nbViews = 0;
		this.cost = cost;

		this.startDate = LocalDate.now();
		this.endDate = startDate.plusMonths(limitMonth);

		this.description = description;
		this.city = city;
	}

	public String getContact() {
		return contact;
	}

	public City getCity() {
		return city;
	}

	public UUID getOfferId() {
		return id;
	}

	public long getCost() {
		return cost;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public User getUserId() {
		return user;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getName() {
		return nameOffer;
	}

	public long getNbViews() {
		return nbViews;
	}

	public String getDescription() {
		return description;
	}

	public SubCategory getSubCategorie() {
		return subCategory;
	}

}
