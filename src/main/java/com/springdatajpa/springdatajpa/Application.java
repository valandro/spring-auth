package com.springdatajpa.springdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.valandro.contract", "com.valandro.impl"})
@EntityScan(basePackages = "com.valandro.impl.data")
@EnableJpaRepositories("com.valandro.impl.repository")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
}
