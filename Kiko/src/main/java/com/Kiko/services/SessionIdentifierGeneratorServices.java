package com.Kiko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.SessionIdentifierGenerator;
import com.Kiko.model.User;
import com.Kiko.repositories.SessionIdentifierGeneratorRepository;
import com.Kiko.repositories.UserRepository;

@Service
public class SessionIdentifierGeneratorServices {

	private SessionIdentifierGeneratorRepository sessionIdentifierGeneratorRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public SessionIdentifierGeneratorServices(
			SessionIdentifierGeneratorRepository sessionIdentifierGeneratorRepository) {
		this.sessionIdentifierGeneratorRepository = sessionIdentifierGeneratorRepository;
	}

	public String save(String idUser) {
		SessionIdentifierGenerator session = sessionIdentifierGeneratorRepository.findByUserId(idUser);
		if (session != null) {
			this.sessionIdentifierGeneratorRepository.delete(session);
		}
		SessionIdentifierGenerator newSession = new SessionIdentifierGenerator(idUser);
		sessionIdentifierGeneratorRepository.save(newSession);
		return idUser;

		// TODO : envoi de code à l'utilisateur
	}

	public User login(String idUser, int code) {
		SessionIdentifierGenerator session = sessionIdentifierGeneratorRepository.findByUserId(idUser);
		if (session != null) {
			if (session.getUserId().equals(idUser) & session.getCode() == code) {
				this.sessionIdentifierGeneratorRepository.delete(session);
				userRepository.save(new User(idUser));
				return this.userRepository.findByUserId(idUser);
			}
		}
		return null;
	}
}
