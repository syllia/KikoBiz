package com.investMessage.web;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.investMessage.model.Customer;
import com.investMessage.model.MessageDefine;
import com.investMessage.repositories.CustomerRepository;
import com.investMessage.services.ClickatellServices;
import com.investMessage.web.DTO.PromosDTO;

@Controller
@RequestMapping(value = "/promos")
public class PromosController {
	private static final Logger logger = LoggerFactory.getLogger(PromosController.class);
	@Autowired
	private CustomerRepository customersRepository;

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> post(@RequestBody @Valid PromosDTO promosDTO) throws IOException, InterruptedException {
		if (promosDTO.message != null && promosDTO.code != null) {
			if (promosDTO.code.equals(MessageDefine.code) && !promosDTO.message.equals("")) {
				for (Customer customer : customersRepository.findAll()) {
					logger.info("send promos" + customer.getId());
					ClickatellServices.sendMessage(promosDTO.message, customer.getNumero());
				}
				return new ResponseEntity<>("Envoyé", HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> postPromostest(@RequestBody @Valid PromosDTO promosDTO)
			throws IOException, InterruptedException {
		if (promosDTO.message != null && promosDTO.code != null) {
			if (promosDTO.code.equals(MessageDefine.code) && !promosDTO.message.equals("")) {
				logger.info("Send promos test");
				ClickatellServices.sendMessage(promosDTO.message, MessageDefine.num1);
				ClickatellServices.sendMessage(promosDTO.message, MessageDefine.num2);
				return new ResponseEntity<>("Envoyé", HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
