package com.valandro.contract;

import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.stub.ContractStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class, properties = {"spring.datasource.url=jdbc:mysql://159.203.67.88:3306/java_auth?verifyServerCertificate=false&useSSL=true",
        "spring.datasource.driver-class-name=com.mysql.jdbc.Driver","spring.datasource.username=root", "spring.datasource.password=Password.123#dificult"})
public class AuthControllerTest extends WebFluxApplicationTests {

    @Before
    public void setUp() {
        super.webTestClient = WebTestClient.bindToApplicationContext(applicationContext).build();
    }
    @Test
    public void authenticate_success() {
        AuthRequest request = ContractStub.getValidRequest();

        super.webTestClient
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

        super.webTestClient
                .post()
                .uri("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(request)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody()
                .jsonPath("$.httpStatus").isEqualTo(HttpStatus.BAD_REQUEST.value())
                .jsonPath("$.message").isEqualTo("Usuário não encontrado.");
    }
}