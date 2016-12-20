package com.investMessage.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {

	@Query(value = "SELECT * FROM DOCUMENT WHERE user IN:ACCESSORS", nativeQuery = true)
	List<Document> findByAccessor(@Param("user") User user);

}
