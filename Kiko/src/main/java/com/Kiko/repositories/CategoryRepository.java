package com.Kiko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kiko.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
