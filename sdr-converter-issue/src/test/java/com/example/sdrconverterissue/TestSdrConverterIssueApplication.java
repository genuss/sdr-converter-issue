package com.example.sdrconverterissue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestSdrConverterIssueApplication {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest")).withReuse(true);
	}

	public static void main(String[] args) {
		SpringApplication.from(SdrConverterIssueApplication::main).with(TestSdrConverterIssueApplication.class).run(args);
	}

}
