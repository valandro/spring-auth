package com.valandro.contract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.valandro.contract", "com.valandro.impl"})
@EntityScan(basePackages = "com.valandro.impl.data")
@EnableJpaRepositories(basePackages = "com.valandro.impl.repository")
@SpringBootApplication
public class ApplicationTests {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AuthController.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}
