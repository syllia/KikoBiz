package com.investMessage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.investMessage.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	List<Store> findByStore(String store);
}
