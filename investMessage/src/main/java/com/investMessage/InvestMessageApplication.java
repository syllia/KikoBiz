package com.investMessage;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.investMessage.domain.StoreRepository;
import com.investMessage.domain.UserRepository;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class InvestMessageApplication extends AsyncConfigurerSupport {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(InvestMessageApplication.class, args);

	}

	@Bean
	public CommandLineRunner demo(UserRepository repository, StoreRepository storeRepository) {
		return (args) -> {
			// storeRepository.save(new Store("store"));
			// repository.save(new User("Jack", "Bauer", "lastName", new
			// Store("store"), "ju", "ff", "ffff", "user"));
		};
	}

}
