package com.investMessage;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.investMessage.services.CustomerService;
import com.investMessage.services.UserService;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class InvestMessageApplication extends AsyncConfigurerSupport {
	// private static final Logger log =
	// LoggerFactory.getLogger(InvestMessageApplication.class);

	public static void main(String[] args) throws IOException {
		SpringApplication.run(InvestMessageApplication.class, args);

	}

	@Bean
	public CommandLineRunner demo(UserService service, CustomerService customers) {
		return (args) -> {
			service.saveUser("Adossou", "julio", "admin", "juado");
			service.saveUser("Adossou", "oulioo", "", "12345");
			customers.saveStore("Ceka");
			customers.saveStore("Cole");
		};
	}

}
