package com.Kiko.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kiko.model.SousCategorie;

@Repository
public interface SousCategorieRepository extends JpaRepository<SousCategorie, Integer> {
	List<SousCategorie> findByIdCategorie(int idCategorie);
}
