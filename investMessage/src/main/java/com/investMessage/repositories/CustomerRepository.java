package com.investMessage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.investMessage.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByNumero(String numero);

	List<Customer> findByShopAndNameStartsWithIgnoreCase(String shop, String name);
	List<Customer> findByNameStartsWithIgnoreCase(String text);
	List<Customer> findByShop(String shop);
}
