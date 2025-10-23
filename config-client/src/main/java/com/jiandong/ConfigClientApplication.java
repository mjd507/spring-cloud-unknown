package com.jiandong;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ConfigurationPropertiesScan // including record
public class ConfigClientApplication {

	private static final Logger log = LoggerFactory.getLogger(ConfigClientApplication.class);

	public static void main(String[] args) {
		var applicationContext = SpringApplication.run(ConfigClientApplication.class, args);
		ApplicationProperty applicationProperty = applicationContext.getBean(ApplicationProperty.class);
		log.info("virtual thread enabled: {}", applicationProperty.virtualThreadEnabled);
		DataSourceProperty dataSourceProperty = applicationContext.getBean(DataSourceProperty.class);
		log.info("dataSourceProperties: {}", dataSourceProperty);
		MqProperty mqProperty = applicationContext.getBean(MqProperty.class);
		log.info("mqProperty: {}", mqProperty.brokerUrl());
	}

}

@Component
class ApplicationProperty {

	@Value("${spring.threads.virtual.enabled}")
	String virtualThreadEnabled;

}

@ConfigurationProperties(prefix = "spring.datasource")
record DataSourceProperty(String driverClassName, String url,
						  String username, String password) {

	@Override
	public @NonNull String toString() {
		return "DataSourceProperty{" +
				"driverClassName='" + driverClassName + '\'' +
				", url='" + url + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}

}

@ConfigurationProperties(prefix = "spring.activemq")
record MqProperty(String brokerUrl) {

}