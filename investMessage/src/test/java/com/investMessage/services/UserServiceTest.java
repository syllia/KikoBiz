package com.investMessage.services;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.investMessage.domain.StoreRepository;
import com.investMessage.domain.UserRepository;

public class UserServiceTest {
	@Mock
	UserRepository userRepository;
	@Mock
	StoreRepository storeRepository;
	@InjectMocks
	UserService userService = new UserService(userRepository, storeRepository);

	static final String FIRST_NAME = "First";
	static final String LAST_NAME = "Last";

}
