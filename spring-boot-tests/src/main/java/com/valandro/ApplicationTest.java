package com.valandro;

import okhttp3.mockwebserver.MockWebServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


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
