package com.kouri.OfferServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class OfferServer implements Runnable {
	public static void main(String[] args) {
		new OfferServer().run();
	}

	@Override
	public void run() {
		SpringApplication.run(OfferServer.class);
	}

}
