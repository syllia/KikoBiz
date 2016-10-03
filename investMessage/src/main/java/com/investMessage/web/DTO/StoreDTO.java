package com.investMessage.web.DTO;

import com.investMessage.model.Store;

public class StoreDTO {
	public String store;

	public StoreDTO(Store store) {
		if (store != null) {
			this.store = store.getStore();
		}
	}

	public StoreDTO() {

	}

	public StoreDTO(String store) {
		this.store = store;
	}
}
