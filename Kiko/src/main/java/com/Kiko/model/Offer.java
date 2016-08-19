package com.Kiko.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.Kiko.model.utils.OfferPropreties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler","endDate" })
public class Offer {
	@Id
	private int offerId;
    private long cost;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String name;
    private long nbViews;
    private String description;
    private int idSousCategorie;
    private int number;
    public Offer() {
    	this.startDate=LocalDateTime.now();
		this.endDate=startDate.plusMonths(OfferPropreties.limitMonth);
	}

	public Offer(int id, String p_name,long cost,long nbViews,String description,int idSousCategorie,int number) {
		this.name = p_name;
		this.offerId = id;
		this.cost=cost;
		this.startDate=LocalDateTime.now();
		this.endDate=startDate.plusMonths(OfferPropreties.limitMonth);
		this.nbViews=nbViews;
		this.description=description;
		this.idSousCategorie=idSousCategorie;
		this.number=number;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
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
	public int getNumero() {
		return number;
	}
	public void setNumero(int numero) {
		this.number = numero;
	}

}
