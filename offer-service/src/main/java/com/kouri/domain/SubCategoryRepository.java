package com.kouri.domain;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {
	List<SubCategory> findByIdCategorie(UUID idCategorie);
}
