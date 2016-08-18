package com.Kiko.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kiko.model.Offre;

@Repository
public interface OfferRepository extends JpaRepository<Offre, Integer>{
	List<Offre> findByOrderByStartDateAsc();
	List<Offre> findByIdSousCategorieOrderByStartDateAsc(int idSousCategorie);
	
}
