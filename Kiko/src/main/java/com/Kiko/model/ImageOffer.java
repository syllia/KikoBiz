package com.Kiko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class ImageOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	@JsonProperty("id")
	private int idImageOffer;
	private int idOffer;
	@Lob
	@Column(length=2000)
	@JsonProperty("byteArray")
	private String imgOffer ;
	
	public ImageOffer() {
		// TODO Auto-generated constructor stub
	}
	public ImageOffer(int idOffer,String imgOffer) {
		this.idOffer=idOffer;
		this.imgOffer=imgOffer;
	}
	public int getIdOffer() {
		return idOffer;
	}
	public void setIdOffer(int idOffer) {
		this.idOffer = idOffer;
	}
	public String getImgOffer() {
		return this.imgOffer;
	}
	public void setByteArray(String imgOffer) {
		this.imgOffer = imgOffer;
	}
	public int getIdImageOffer() {
		return idImageOffer;
	}
	
	
}
