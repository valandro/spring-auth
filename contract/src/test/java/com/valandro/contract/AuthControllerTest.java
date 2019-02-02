package com.valandro.contract;

import com.valandro.ApplicationTest;
import com.valandro.WebFluxApplicationTests;
import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.stub.ContractStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AuthControllerTest extends WebFluxApplicationTests {

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Test
    public void authenticate_success() {
        AuthRequest request = ContractStub.getValidRequest();

        this.webTestClient
                .post()
                .uri("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.user_name").isEqualTo(request.getUsername());
    }

    @Test
    public void authenticate_not_found() {
        AuthRequest request = ContractStub.getInvalidRequest();

        this.webTestClient
                .post()
                .uri("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(request)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo(HttpStatus.NOT_FOUND.value())
                .jsonPath("$.message").isEqualTo("Usuário não encontrado.");
    }
}