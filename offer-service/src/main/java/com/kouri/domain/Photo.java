package com.kouri.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Photo {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")

	private UUID id;
	@OneToOne(cascade = CascadeType.ALL)
	private Offer offer;
	@Lob
	private byte[] imgOffer;

	public Photo() {
	}

	public Photo(byte[] imgOffer, Offer offer) {
		this.imgOffer = imgOffer;
		this.offer = offer;
	}

	public byte[] getImgOffer() {
		return this.imgOffer;
	}

	public UUID getIdImageOffer() {
		return id;
	}
}
