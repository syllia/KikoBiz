package com.Kiko.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kiko.model.ImageOffer;
@Repository
public interface ImageOfferRepository extends JpaRepository<ImageOffer, Integer>{
	List<ImageOffer> findByIdOffer(int idOffer);
}
