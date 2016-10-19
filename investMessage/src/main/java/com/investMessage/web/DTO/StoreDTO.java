package com.investMessage.web.DTO;

import com.investMessage.model.Store;

public class StoreDTO {
	public String store;
	public String code;

	public StoreDTO() {

	}

	public StoreDTO(Store store) {
		if (store != null) {
			this.store = store.getStore();
		}
	}

	public StoreDTO(String store, String code) {
		this.store = store;
		this.code = code;
	}
}
