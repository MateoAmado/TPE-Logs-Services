package com.example.Logs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.example.Logs.Repository")
@SpringBootApplication
public class LogsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogsServiceApplication.class, args);
	}

}
