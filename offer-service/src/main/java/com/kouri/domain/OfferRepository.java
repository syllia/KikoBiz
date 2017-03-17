package com.Kiko.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kiko.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>{
	List<Offer> findByOrderByStartDateAsc();
	//List<Offer> findByOrderByStartDateDsc();
	List<Offer> findByIdSousCategorieOrderByStartDateAsc(int idSousCategorie);
}
