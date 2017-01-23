package com.investMessage.services;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investMessage.domain.StoreRepository;
import com.investMessage.domain.User;
import com.investMessage.domain.UserRepository;
import com.investMessage.web.DTO.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StoreRepository storeRepository;
	private static final Random RANDOM = new SecureRandom();
	public static final int PASSWORD_LENGTH = 8;

	public UserService() {
	}

	public UserService(UserRepository customerRepository, StoreRepository storeRepository) {

		this.userRepository = customerRepository;
		this.storeRepository = storeRepository;
	}

	public UserDTO saveUser(String firstName, String lastName, String type, String pass)
			throws UserIsAlreadyRegisteredException {
		String username = generateUsername(firstName, lastName);
		User user = userRepository.findOne(username);

		System.out.println(pass);
		System.out.println(username);

		if (user == null) {
			userRepository.save(new User(username, firstName, lastName, pass, type));
			return new UserDTO(userRepository.findByUserNameIgnoreCaseAndPassWord(username, pass));
		} else {
			throw new UserIsAlreadyRegisteredException();
		}

	}

	private String generateUsername(String firstName, String lastName) {
		return (lastName.substring(0, 2) + firstName.substring(0, 3)).toLowerCase();
	}

	public String generateRandomPassword(String username) {
		Random rn = new Random();
		int answer = rn.nextInt(10) + 1;
		String letters = username + Integer.toString(answer);
		return letters;
	}

	public UserDTO findUserBy(String userName, String passWord) throws UserNotFoundException {
		if (userRepository.findByUserNameIgnoreCaseAndPassWord(userName, passWord) != null) {
			return new UserDTO(userRepository.findByUserNameIgnoreCaseAndPassWord(userName, passWord));
		}
		throw new UserNotFoundException();

	}

	public List<UserDTO> findAll() {
		List<UserDTO> usersDTO = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			usersDTO.add(new UserDTO(user));
		}
		return usersDTO;
	}
}
