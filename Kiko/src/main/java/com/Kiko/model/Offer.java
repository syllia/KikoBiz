package com.Kiko.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.Kiko.model.utils.OfferPropreties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	@JsonProperty("offerId")
	private int offerId;
	@JsonProperty("userId")
	private int userId;
	@JsonProperty("idSousCategorie")
	private int idSousCategorie;
	@JsonProperty("name")
	private String name;
	@JsonProperty("contact")
	private int contact;
	@JsonProperty("nbViews")
	private long nbViews;
	@JsonProperty("cost")
	private long cost;

	@DateTimeFormat
	@JsonProperty("startDate")
	private LocalDate startDate;
	@DateTimeFormat
	@JsonProperty("endDate")
	private LocalDate endDate;
	@JsonProperty("exampleDescription")
	private String exampleDescription;
	@JsonProperty("description")
	private String description;

	@JsonProperty("country")
	private String country;
	@JsonProperty("city")
	private String city;

	@ElementCollection
	private List<Integer> photos;

	public Offer() {
		this.startDate = LocalDate.now();
		this.endDate = startDate.plusMonths(OfferPropreties.limitMonth);
		this.exampleDescription = "examDescript";
	}

	public Offer(int userId, String country, String city, String p_name, long cost, long nbViews, String description,
			int idSousCategorie, int contact) {
		this.userId = userId;
		this.idSousCategorie = idSousCategorie;
		this.name = p_name;
		this.contact = contact;
		this.nbViews = nbViews;
		this.cost = cost;

		this.startDate = LocalDate.now();
		this.endDate = startDate.plusMonths(OfferPropreties.limitMonth);

		this.description = description;
		this.exampleDescription = "examDescript";
		this.country = country;
		this.city = city;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Integer> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Integer> photos) {
		this.photos = photos;
	}

	public int getOfferId() {
		return offerId;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		userId = userId;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getExampleDescription() {
		return exampleDescription;
	}

	public void setExampleDescription(String exampleDescription) {
		this.exampleDescription = exampleDescription;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNbViews() {
		return nbViews;
	}

	public void setNbViews(long nbViews) {
		this.nbViews = nbViews;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdSousCategorie() {
		return idSousCategorie;
	}

	public void setIdSousCategorie(int idSousCategorie) {
		this.idSousCategorie = idSousCategorie;
	}

	public int getContacts() {
		return contact;
	}

	public void setContacts(int contact) {
		this.contact = contact;
	}

}
