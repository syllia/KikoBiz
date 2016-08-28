package com.Kiko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class ImageOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private int idImageOffer;
	private int idOffer;
	@Lob
	@Column(length=10000)
	private byte[] byteArray ;
	
	public ImageOffer() {
		// TODO Auto-generated constructor stub
	}
	public ImageOffer(int idOffer,byte[]byteArray) {
		this.idOffer=idOffer;
		this.byteArray=byteArray;
	}
	public int getIdOffer() {
		return idOffer;
	}
	public void setIdOffer(int idOffer) {
		this.idOffer = idOffer;
	}
	public byte[] getByteArray() {
		return byteArray;
	}
	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}
	public int getIdImageOffer() {
		return idImageOffer;
	}
	
	
}
