package com.Kiko.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Kiko.model.User;
import com.Kiko.services.SessionIdentifierGeneratorServices;

@RestController
public class SessionIdentifierController {
	@Autowired private SessionIdentifierGeneratorServices sessionIdentifierGeneratorServices ;
	@Autowired private com.Kiko.services.UserService UserService;
	
	@RequestMapping(value="/generatecode/{id}", method = RequestMethod.GET)
	public String save(@PathVariable String id){
		return sessionIdentifierGeneratorServices.save(id);
	}
	
	@RequestMapping(value="/login/{id}/{code}", method = RequestMethod.GET)
	public ResponseEntity<User> valideCode(@PathVariable String id,@PathVariable int code){
		User user =  sessionIdentifierGeneratorServices.delete(id, code);
		//if (user==null){return new ResponseEntity<User>(user, HttpStatus.NOT_ACCEPTABLE);}
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
}
