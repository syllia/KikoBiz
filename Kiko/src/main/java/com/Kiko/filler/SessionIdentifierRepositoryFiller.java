package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;

import com.Kiko.repositories.SessionIdentifierGeneratorRepository;

public class SessionIdentifierRepositoryFiller {
	@Autowired
	public SessionIdentifierRepositoryFiller(SessionIdentifierGeneratorRepository sessionIdentifierGeneratorRepository) {
	}
}
