package com.Kiko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kiko.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
