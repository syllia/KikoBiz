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
	UserDTO updateUserDTO;

	@Before
	public void setUp() {
		user = new User("lafir", FIRST_NAME, LAST_NAME, new Store(STORE), "password", "emailAddress", "phoneNumber");
		updatedUser = new User("lafir", FIRST_NAME, LAST_NAME, new Store(STORE), "pass", "email", "phone");
		userDTO = new UserDTO(user);
		updateUserDTO = new UserDTO(updatedUser);

	}

	@Test
	public void userDTO_updatesInfos_infosUpdated() throws UserNotFoundException, StoreNotFoundException {
		Mockito.when(userRepository.findOne(userDTO.userName)).thenReturn(user);
		Mockito.when(storeRepository.findOne(userDTO.store)).thenReturn(new Store(userDTO.store));
		userService.updateInfos(updateUserDTO);
		assertEquals(userRepository.findOne(updateUserDTO.userName), updatedUser);
	}

	@Test(expected = UserNotFoundException.class)
	public void userDTO_updatesInfos_UserNotFound() throws UserNotFoundException, StoreNotFoundException {
		Mockito.when(userRepository.findOne(userDTO.userName)).thenReturn(null);
		Mockito.when(storeRepository.findOne(userDTO.store)).thenReturn(new Store(userDTO.store));
		userService.updateInfos(userDTO);
	}

	@Test(expected = UserIsAlreadyRegisteredException.class)
	public void userDTO_saveUser_UserIsAlreadyRegisteredException() throws UserIsAlreadyRegisteredException {
		Mockito.when(userRepository.findOne(userDTO.userName)).thenReturn(user);

		Mockito.when(storeRepository.findOne(userDTO.store)).thenReturn(new Store(userDTO.store));
		userService.saveUser(userDTO.firstName, userDTO.lastName, new Store(userDTO.store), userDTO.emailAddress,
				userDTO.phoneNumber);

	}

	@Test
	public void userDTO_saveUser_UserIsSaved() throws UserIsAlreadyRegisteredException {
		Mockito.when(userRepository.findOne(userDTO.userName)).thenReturn(null);
		Mockito.when(storeRepository.findOne(userDTO.store)).thenReturn(new Store(userDTO.store));
		userService.saveUser(userDTO.firstName, userDTO.lastName, new Store(userDTO.store), userDTO.emailAddress,
				userDTO.phoneNumber);
		Mockito.verify(userRepository, Mockito.times(1)).save(user);
	}

	@Test(expected = UserNotFoundException.class)
	public void userDTO_findUserBy_throwUserNotFoundException() throws UserNotFoundException {
		Mockito.when(userRepository.findByUserNameIgnoreCaseAndPassWord(userDTO.userName, userDTO.passWord))
				.thenReturn(null);
		userService.findUserBy(userDTO.userName, userDTO.passWord);

	}

	@Test
	public void userDTO_findUserBy_UserIsSaved() throws UserNotFoundException {
		Mockito.when(userRepository.findByUserNameIgnoreCaseAndPassWord(userDTO.userName, userDTO.passWord))
				.thenReturn(user);
		assertEquals(userDTO, userService.findUserBy(userDTO.userName, userDTO.passWord));

	}

}
