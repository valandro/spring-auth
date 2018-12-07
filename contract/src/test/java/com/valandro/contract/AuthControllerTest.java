package com.valandro.contract;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valandro.contract.binder.AuthContractBinder;
import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.response.AuthResponse;
import com.valandro.contract.stub.ContractStub;
import com.valandro.impl.binder.AuthImplBinder;
import com.valandro.impl.data.UserEntity;
import com.valandro.impl.model.AuthModel;
import com.valandro.impl.model.ImplModel;
import com.valandro.impl.service.AuthService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTests.class)

public class AuthControllerTest {


    private WebTestClient webTestClient;

    @Autowired
    protected ApplicationContext applicationContext;

    @MockBean
    private AuthService service;

    @Before
    public void before() {
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Test
    public void authenticate_success() throws Exception {
        AuthRequest request = ContractStub.getValidRequest();
        AuthResponse response = ContractStub.getValidResponse();
        UserEntity entity = ContractStub.validEntity();
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

        doReturn(Optional.of(entity))
                .when(this.service)
                .findUserByNameAndPassword(eq(AuthContractBinder.authRequestBinder(request)));

        ImplModel implModel = AuthImplBinder.bindToImplModel(entity);

        doReturn(token)
                .when(this.service)
                .generateToken(eq(implModel));

        AuthModel authModel = AuthImplBinder.bindToAuthModel(implModel, this.service.generateToken(implModel));

        doReturn(authModel)
                .when(this.service)
                .createAuthModel(eq(implModel));

        this.webTestClient
                .post()
                .uri("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.user_name").isEqualTo(response.getName())
                .jsonPath("$.token").isEqualTo(response.getToken())
                .jsonPath("$.access_level").isEqualTo(response.getAccessLevel());


    }
}