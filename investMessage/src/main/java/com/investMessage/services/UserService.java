package com.investMessage.services;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investMessage.domain.Store;
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

	public UserService() {
	}

	private static final Random RANDOM = new SecureRandom();
	public static final int PASSWORD_LENGTH = 8;

	public UserService(UserRepository customerRepository, StoreRepository storeRepository) {
		this.userRepository = customerRepository;
		this.storeRepository = storeRepository;
	}

	public void saveUser(String firstName, String lastName, Store store, String email, String phoneNumber)
			throws CustomerIsAlreadyRegisteredException, UserIsAlreadyRegisteredException {
		String username = generateUsername(firstName, lastName);
		String pass = generateRandomPassword(username);
		User user = userRepository.findOne(username);
		storeRepository.saveAndFlush(store);
		store = storeRepository.findOne(store.getName());
		if (user == null && store != null) {
			System.out.println(username);
			System.out.println(pass);
			userRepository.save(new User(username, firstName, lastName, store, pass, email, phoneNumber));
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

	public UserDTO findUserBy(String userName, String passWord) {
		return new UserDTO(userRepository.findByUserNameAndPassWordIgnoreCase(userName, passWord));
	}

	public void updateInfos(UserDTO userDTO) throws UserNotFoundException, StoreNotFoundException {
		User user = userRepository.findOne(userDTO.userName);
		Store store = storeRepository.findOne(userDTO.store);
		if (user != null && store != null) {
			user.update(userDTO.passWord, userDTO.phoneNumber, userDTO.emailAddress);
			userRepository.save(user);
		} else {
			if (user == null)
				throw new UserNotFoundException();
			else {
				throw new StoreNotFoundException();
			}
		}

	}
}
