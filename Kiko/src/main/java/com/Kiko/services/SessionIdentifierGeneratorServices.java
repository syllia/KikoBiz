package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.Category;
import com.Kiko.model.SessionIdentifierGenerator;
import com.Kiko.model.User;
import com.Kiko.repositories.CategoryRepository;
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
		List<SessionIdentifierGenerator> sessions = sessionIdentifierGeneratorRepository.findByUserId(idUser);
		if (sessions.size() > 0) {
			this.sessionIdentifierGeneratorRepository.delete(sessions);
		}
		sessionIdentifierGeneratorRepository.save(new SessionIdentifierGenerator(idUser));
		return idUser;
		//envoi de code
	}
	public User delete(String idUser,int code) {
		List<SessionIdentifierGenerator> sessions = sessionIdentifierGeneratorRepository.findByUserId(idUser);
		
		if (sessions.size() !=1) {
			this.sessionIdentifierGeneratorRepository.delete(sessions);
		}else{
			if (sessions.get(0).getUserId()==idUser &sessions.get(0).getCode()==code){
				this.sessionIdentifierGeneratorRepository.delete(sessions);
					userRepository.save(new User(idUser));
					return this.userRepository.findByUserId(idUser);
			}
		}
		return null;
	}
}
