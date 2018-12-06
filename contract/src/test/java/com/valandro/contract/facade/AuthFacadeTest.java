package com.valandro.contract.facade;

import com.valandro.contract.binder.AuthContractBinder;
import com.valandro.contract.exception.ApiException;
import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.stub.ContractStub;
import com.valandro.impl.binder.AuthImplBinder;
import com.valandro.impl.data.UserEntity;
import com.valandro.impl.model.AuthModel;
import com.valandro.impl.model.ImplModel;
import com.valandro.impl.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AuthFacadeTest {

    @InjectMocks
    private AuthFacade facade;

    @Mock
    private AuthService authService;

    @Test
    public void login() {
        AuthRequest request = ContractStub.getValidRequest();
        UserEntity entity = ContractStub.validEntity();
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

        doReturn(Optional.of(entity))
                .when(this.authService)
                .findUserByNameAndPassword(eq(AuthContractBinder.authRequestBinder(request)));

        ImplModel implModel = AuthImplBinder.bindToImplModel(entity);

        doReturn(token)
                .when(this.authService)
                .generateToken(eq(implModel));

        AuthModel authModel = AuthImplBinder.bindToAuthModel(implModel, this.authService.generateToken(implModel));

        doReturn(authModel)
                .when(this.authService)
                .createAuthModel(eq(implModel));

        Mono<com.valandro.contract.response.AuthResponse> result = this.facade.login(request);

        com.valandro.contract.response.AuthResponse response = result.block();

        assertEquals(response.getToken(), token);
        assertEquals(response.getAccessLevel(), authModel.getAccessLevel());
        assertEquals(response.getName(), authModel.getName());
    }

    @Test(expected = ApiException.class)
    public void user_not_found() {
        AuthRequest request = ContractStub.getValidRequest();

        doReturn(Optional.empty())
                .when(this.authService)
                .findUserByNameAndPassword(eq(AuthContractBinder.authRequestBinder(request)));

        com.valandro.contract.response.AuthResponse result = this.facade.login(request).block();
    }
}