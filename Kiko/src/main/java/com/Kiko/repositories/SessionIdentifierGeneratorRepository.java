package com.Kiko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Kiko.model.SessionIdentifierGenerator;

@Repository
public interface SessionIdentifierGeneratorRepository extends JpaRepository<SessionIdentifierGenerator, Integer> {
	SessionIdentifierGenerator findByUserId(String userId);
}
