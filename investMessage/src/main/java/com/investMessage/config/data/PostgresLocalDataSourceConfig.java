package com.investMessage.config.data;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("postgres-local")
public class PostgresLocalDataSourceConfig extends AbstractDataSourceConfig {

	@Bean
	public DataSource dataSource() {
		return createDataSource("jdbc:postgresql://localhost/customers", "org.postgresql.Driver", "postgres",
				"postgres");
	}

}
