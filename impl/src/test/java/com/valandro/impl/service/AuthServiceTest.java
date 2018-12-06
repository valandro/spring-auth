package com.valandro.impl.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valandro.impl.data.UserEntity;
import com.valandro.impl.exception.ImplException;
import com.valandro.impl.model.AuthModel;
import com.valandro.impl.model.ImplModel;
import com.valandro.impl.model.ImplRequest;
import com.valandro.impl.repository.UserRepository;
import com.valandro.impl.stub.ImplStub;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Spy
    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(authService, "sessionTime", 24);
        ReflectionTestUtils.setField(authService, "securityKey", "F69C9E7D5E00B1C38010751BABC2D28A575613F4BED3851F50FC5F4055134C7D");
    }

    @Test
    public void create_auth_model() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
        ImplModel model = ImplStub.validImplModel();

        doReturn(token)
                .when(this.authService)
                .generateToken(eq(model));

        AuthModel result = this.authService.createAuthModel(model);

        assertEquals(result.getName(), model.getName());
        assertEquals(result.getClientId(), model.getClientId());
        assertEquals(result.getAccessLevel(), model.getAccessLevel());
        assertEquals(result.getBlocked(), model.getBlocked());
        assertEquals(result.getToken(), token);
    }

    @Test
    public void find_user_by_name_and_password_return_entity() {
        UserEntity entity = ImplStub.validEntity();
        ImplRequest request = ImplStub.validImplRequest();

        doReturn(entity)
                .when(this.userRepository)
                .findByNameAndPassword(eq(request.getUsername()),eq(request.getPassword()));

        Optional<UserEntity> result = this.authService.findUserByNameAndPassword(request);

        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), entity.getId());
        assertEquals(result.get().getName(), entity.getName());
        assertEquals(result.get().getPassword(), entity.getPassword());
        assertEquals(result.get().getAccessLevel(), entity.getAccessLevel());
        assertEquals(result.get().getClientId(), entity.getClientId());
        assertEquals(result.get().getBlocked(), entity.getBlocked());
    }

    @Test
    public void find_user_by_name_and_password_not_return_entity() {
        UserEntity entity = ImplStub.notFoundEntity();
        ImplRequest request = ImplStub.validImplRequest();

        doReturn(entity)
                .when(this.userRepository)
                .findByNameAndPassword(eq(request.getUsername()),eq(request.getPassword()));

        Optional<UserEntity> result = this.authService.findUserByNameAndPassword(request);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void generate_token() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ImplModel model = ImplStub.validImplModel();

        doReturn(objectMapper.writeValueAsString(model))
                .when(this.objectMapper)
                .writeValueAsString(eq(model));

        String result = this.authService.generateToken(model);

        assertNotNull(result);
        assertThat(result, any(String.class));
    }

    @Test(expected = ImplException.class)
    public void generate_token_throw_exception() throws Exception {
        ImplModel model = ImplStub.validImplModel();

        doThrow(JsonProcessingException.class)
                .when(this.objectMapper)
                .writeValueAsString(eq(model));

        String result = this.authService.generateToken(model);
    }
}