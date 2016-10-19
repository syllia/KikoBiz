package com.investMessage.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.investMessage.model.Store;
import com.investMessage.repositories.StoreRepository;
import com.investMessage.web.DTO.StoreDTO;

@RestController
@RequestMapping(value = "/stores")
public class StoreController {

	private StoreRepository repository;

	@Autowired
	public StoreController(StoreRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<StoreDTO>> shop() {
		List<StoreDTO> stores = repository.findAll().stream().map(store -> {
			StoreDTO storeDTO = new StoreDTO(store);
			return storeDTO;
		}).collect(Collectors.toList());

		return new ResponseEntity<>(stores, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> post(@RequestBody StoreDTO storeDTO) {
		if (storeDTO.store != null) {
			List<Store> stores = repository.findByStore(storeDTO.store);
			if (stores.isEmpty()) {
				Store store = new Store(storeDTO.store, storeDTO.code);
				store = repository.save(store);
				return new ResponseEntity<>(new StoreDTO(store), HttpStatus.CREATED);
			}
			return new ResponseEntity<>(storeDTO, HttpStatus.ALREADY_REPORTED);
		}
		return new ResponseEntity<>(storeDTO, HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		Store store = repository.findOne(id);
		if (store != null) {
			repository.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
