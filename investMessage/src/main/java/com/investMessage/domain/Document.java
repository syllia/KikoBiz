package com.investMessage.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Document {
	@Id
	private String id;
	private String name;
	private String date;
	@OneToOne(cascade = CascadeType.ALL)
	private User creator;
	private String description;

	public Document() {

	}

	public Document(String id, String name, String date, User creator, String description) {
		this.name = name;
		this.id = id;
		this.date = date;
		this.creator = creator;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public User getCreator() {
		return creator;
	}

	public String getDescription() {
		return description;
	}

}
