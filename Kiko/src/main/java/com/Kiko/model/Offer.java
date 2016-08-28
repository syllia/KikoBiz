package com.Kiko.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.Kiko.model.utils.OfferPropreties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler","endDate" })
public class Offer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int offerId;
	private int UserId;
    
    private int idSousCategorie;
    private String name;
    private List<Integer> contacts;
    private long nbViews;
    private long cost;
    
    @DateTimeFormat
    private LocalDate startDate;
    @DateTimeFormat
    private LocalDate endDate;
    
    private String exampleDescription;
    private String description;
   
    
    public Offer() {
    	this.startDate=LocalDate.now();
		this.endDate=startDate.plusMonths(OfferPropreties.limitMonth);
		this.exampleDescription="examDescript";
	}

	public Offer(int userId, String p_name,long cost,long nbViews,String description,int idSousCategorie,List<Integer>contact) {
		this.UserId=userId;
		this.idSousCategorie=idSousCategorie;
		this.name = p_name;
		this.contacts =contact;
		this.nbViews=nbViews;
		this.cost=cost;
		
		
		this.startDate=LocalDate.now();
		this.endDate=startDate.plusMonths(OfferPropreties.limitMonth);
		
		this.description=description;
		this.exampleDescription="examDescript";
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
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
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

	public List<Integer> getContacts() {
		return contacts;
	}

	public void setContacts(List<Integer> contacts) {
		this.contacts = contacts;
	}
	

}
