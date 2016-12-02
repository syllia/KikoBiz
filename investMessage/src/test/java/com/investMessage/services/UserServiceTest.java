package com.investMessage.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.investMessage.domain.Store;
import com.investMessage.domain.StoreRepository;
import com.investMessage.domain.User;
import com.investMessage.domain.UserRepository;
import com.investMessage.web.DTO.UserDTO;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock
	UserRepository userRepository;
	@Mock
	StoreRepository storeRepository;
	@InjectMocks
	UserService userService = new UserService(userRepository, storeRepository);

	static final String FIRST_NAME = "First";
	static final String LAST_NAME = "Last";
	static final String STORE = "Store";

	User user;
	User updatedUser;
	UserDTO userDTO;

	@Before
	public void setUp() {
		user = new User("filas", FIRST_NAME, LAST_NAME, new Store(STORE), "password", "emailAddress", "phoneNumber",
				"role");
		updatedUser = new User("filas", FIRST_NAME, LAST_NAME, new Store(STORE), "pass", "email", "phone", "roles");
		userDTO = new UserDTO(updatedUser);

	}

	@Test
	public void userDTO_updatesInfos_infosUpdated() throws UserNotFoundException, StoreNotFoundException {
		Mockito.when(userRepository.findOne(userDTO.userName)).thenReturn(user);
		Mockito.when(storeRepository.findOne(userDTO.store)).thenReturn(new Store(userDTO.store));
		userService.updateInfos(userDTO);
		assertEquals(userRepository.findOne(userDTO.userName), updatedUser);

	}

}
