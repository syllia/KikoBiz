package com.investMessage.config.data;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mysql-local")
public class MySqlLocateDataSourceConfig extends AbstractDataSourceConfig {

	@Bean
	public DataSource dataSource() {
		return createDataSource("jdbc:mysql://localhost/music", "com.mysql.jdbc.Driver", "", "");
	}

}
