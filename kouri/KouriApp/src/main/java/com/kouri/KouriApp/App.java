package com.kouri.KouriApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.kouri.OfferServer.OfferServer;

@EnableAutoConfiguration
public class App {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(OfferServer.class);
		OfferServer.main(args);
	}

}
