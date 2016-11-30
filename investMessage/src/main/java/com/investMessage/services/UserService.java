package com.investMessage.services;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investMessage.domain.Store;
import com.investMessage.domain.StoreRepository;
import com.investMessage.domain.User;
import com.investMessage.domain.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StoreRepository storeRepository;

	public UserService() {
	}

	private static final Random RANDOM = new SecureRandom();
	public static final int PASSWORD_LENGTH = 8;

	public UserService(UserRepository customerRepository, StoreRepository storeRepository) {
		this.userRepository = customerRepository;
		this.storeRepository = storeRepository;
	}

	public void saveCustomer(String firstName, String lastName, Store store)
			throws CustomerIsAlreadyRegisteredException, UserIsAlreadyRegisteredException {
		String username = generateUsername(firstName, lastName);
		String pass = generateRandomPassword(username);
		User user = userRepository.findOne(username);
		storeRepository.saveAndFlush(store);
		store = storeRepository.findOne(store.getName());
		if (user == null && store != null) {
			System.out.println(username);
			System.out.println(pass);
			userRepository.save(new User(username, firstName, lastName, store, pass));
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

	public User getUserBy(String userName, String passWord) {
		return userRepository.findByUserNameAndPassWordIgnoreCase(userName, passWord);
	}
}
