package com.investMessage.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Store {

	@Id
	private String name;

	public Store() {
	}

	public Store(String store) {
		this.name = store;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Store) {
			Store store = (Store) object;
			return (this.name.equals(store.name));
		} else {
			return false;
		}
	}
}
