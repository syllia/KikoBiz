package com.investMessage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.investMessage.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByNumero(String numero);

	List<Customer> findByShopAndNumeroStartsWithIgnoreCase(String shop, String numero);

	List<Customer> findByShop(String shop);
}
