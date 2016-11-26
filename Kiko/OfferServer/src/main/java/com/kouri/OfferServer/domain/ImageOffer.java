package com.kouri.OfferServer.domain;

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
	
	@Lob
	
	@JsonProperty("byteArray")
	private byte[] imgOffer ;
	
	public ImageOffer() {
		// TODO Auto-generated constructor stub
	}
	public ImageOffer(byte[] imgOffer) {
		this.imgOffer=imgOffer;
	}
	
	public byte[] getImgOffer() {
		return this.imgOffer;
	}
	public void setByteArray(byte[] imgOffer) {
		this.imgOffer = imgOffer;
	}
	public int getIdImageOffer() {
		return idImageOffer;
	}
	
	
}
