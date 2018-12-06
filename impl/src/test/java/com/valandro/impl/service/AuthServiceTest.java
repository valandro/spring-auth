package com.valandro.impl.service;

import com.valandro.impl.data.UserEntity;
import com.valandro.impl.model.AuthModel;
import com.valandro.impl.model.ImplModel;
import com.valandro.impl.model.ImplRequest;
import com.valandro.impl.repository.UserRepository;
import com.valandro.impl.stub.ImplStub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Spy
    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

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

}