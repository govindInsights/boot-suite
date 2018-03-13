package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@PropertySource("classpath:application.properties")
@EnableJpaRepositories
@EnableMongoRepositories
@SpringBootApplication
public class BootDbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootDbServiceApplication.class, args);
	}
}
