package com.Kiko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kiko.model.Country;
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
