package com.kouri.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
	List<Offer> findByOrderByStartDateAsc();

	List<Offer> findBySubCategoryOrderByStartDateAsc(SubCategory subCategory);

	List<Offer> findByCityOrderByStartDateAsc(City city);
}
