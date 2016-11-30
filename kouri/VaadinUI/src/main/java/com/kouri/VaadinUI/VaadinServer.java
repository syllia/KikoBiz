package com.kouri.VaadinUI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kouri.OfferServer.OfferServer;

@SpringBootApplication
public class VaadinServer {
	public static void main(String[] args) {
		SpringApplication.run(OfferServer.class);
	}

}
