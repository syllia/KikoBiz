package com.kouri.OfferServer.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", })
public class Offer {
	public static final int LIMIT_MONTH = 6;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int offerId;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	@OneToOne(cascade = CascadeType.ALL)
	private SubCategory subCategory;
	private String name;
	private String contact;
	private long nbViews;
	private long cost;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String description;
	@OneToOne(cascade = CascadeType.ALL)
	private City city;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ImageOffer> photos;

	public Offer() {
		this.startDate = LocalDateTime.now();
		this.endDate = startDate.plusMonths(LIMIT_MONTH);
	}

	public Offer(User user, City city, String p_name, long cost, String description, SubCategory subCategory,
			String contact) {
		this.user = user;
		this.subCategory = subCategory;
		this.name = p_name;
		this.contact = contact;
		this.cost = cost;
		this.startDate = LocalDateTime.now();
		this.endDate = startDate.plusMonths(LIMIT_MONTH);
		this.description = description;
		this.city = city;
	}

	public int getOfferId() {
		return offerId;
	}

	public User getUser() {
		return user;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public String getName() {
		return name;
	}

	public String getContact() {
		return contact;
	}

	public long getNbViews() {
		return nbViews;
	}

	public long getCost() {
		return cost;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public String getDescription() {
		return description;
	}

	public City getCity() {
		return city;
	}

	public List<ImageOffer> getPhotos() {
		return photos;
	}

	public void updateNbViews() {
		this.nbViews++;
	}

	public Boolean isOutDated() {
		return endDate.isBefore(LocalDateTime.now());
	}

}
