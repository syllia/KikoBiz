package com.investMessage.config.data;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "mysql-cloud", "postgres-cloud", "oracle-cloud" })
public class RelationalCloudDataSourceConfig extends AbstractCloudConfig {

	@Bean
	public DataSource dataSource() {
		return connectionFactory().dataSource();
	}

}
