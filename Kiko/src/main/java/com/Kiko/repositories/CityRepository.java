package com.Kiko.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kiko.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	List<City> findByIdCountry(int idCountry);
}
