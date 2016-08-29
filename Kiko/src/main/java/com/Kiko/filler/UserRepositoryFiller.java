package com.Kiko.filler;

import org.springframework.beans.factory.annotation.Autowired;

import com.Kiko.repositories.SubCategoryRepository;
import com.Kiko.repositories.UserRepository;

public class UserRepositoryFiller {
	@Autowired
	public UserRepositoryFiller(UserRepository userRepository) {
	}
}
