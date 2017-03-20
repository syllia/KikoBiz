package com.kouri.domain;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, UUID> {
	// List<Photo> findBySubCategory(SubCategory subCategory);
}
