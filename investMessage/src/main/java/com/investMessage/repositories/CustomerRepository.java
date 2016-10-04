package com.investMessage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.investMessage.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	List<Customer> findByNumber(String number);

	List<Customer> findByShop(String shop);
}
