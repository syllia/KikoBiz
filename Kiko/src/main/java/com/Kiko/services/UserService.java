package com.Kiko.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kiko.model.SubCategory;
import com.Kiko.model.User;
import com.Kiko.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	
	@Autowired
	public UserService (UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findById(int id) {
		return userRepository.getOne(id);
	}
	public List<User> findAll() {
		return userRepository.findAll();
	}

	
}
