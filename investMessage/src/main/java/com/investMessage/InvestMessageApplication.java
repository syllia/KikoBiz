package com.investMessage;

import java.io.IOException;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.dropbox.core.DbxException;
import com.investMessage.domain.Store;
import com.investMessage.domain.StoreRepository;
import com.investMessage.domain.User;
import com.investMessage.domain.UserRepository;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class InvestMessageApplication extends AsyncConfigurerSupport {

	private static final Logger log = LoggerFactory.getLogger(InvestMessageApplication.class);

	public static void main(String[] args) throws IOException, DbxException {
		SpringApplication.run(InvestMessageApplication.class, args);

	}

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("sendMessage");
		executor.initialize();
		return executor;
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository, StoreRepository storeRepository) {
		return (args) -> {
			storeRepository.save(new Store("store"));
			repository.save(new User("Jack", "Bauer", "lastName", new Store("store"), "ju", "ff", "ffff", "user"));
		};
	}

}
