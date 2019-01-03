package com.valandro.contract;

import okhttp3.mockwebserver.MockWebServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@ComponentScan(basePackages = {"com.valandro.contract", "com.valandro.impl"})
@EntityScan(basePackages = "com.valandro.impl.data")
@EnableJpaRepositories("com.valandro.impl.repository")
@SpringBootApplication
public class ApplicationTest {

    @Bean
    public WebClient webClient(MockWebServer mockWebServer) {
        return WebClient.create(mockWebServer.url("/").toString());
    }

    @Bean
    public MockWebServer mockWebServer() {
        return new MockWebServer();
    }

}
