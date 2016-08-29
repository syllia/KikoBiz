package com.Kiko.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kiko.model.SubCategory;
import com.Kiko.model.User;
import com.Kiko.services.UserService;

@RestController
public class UserController {
	@Autowired private UserService userService;
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getById(@PathVariable int id){
		User user =  userService.findById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		List<User> users =  userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}
