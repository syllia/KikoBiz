package com.investMessage.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findByNumberAndStore(String number, String store);

	List<Customer> findByStoreAndNameStartsWithIgnoreCase(String store, String name);

	List<Customer> findByStoreAndNumberStartsWithIgnoreCase(String store, int number);

	List<Customer> findByStoreOrderByNameAsc(String store);
}
