package com.valandro.contract.binder;

import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.response.AuthResponse;
import com.valandro.impl.model.AuthModel;
import com.valandro.impl.model.ImplRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthContractBinderTest {

    @Test
    public void constructor(){
        AuthContractBinder binder = new AuthContractBinder();
        assertNotNull(binder);
    }

    @Test
    public void authResponseBinder() {
        AuthModel model = validModel();
        AuthResponse result = AuthContractBinder.authResponseBinder(model);
        assertEquals(result.getName(),model.getName());
        assertEquals(result.getAccessLevel(),model.getAccessLevel());
        assertEquals(result.getToken(),model.getToken());
    }

    @Test
    public void authRequestBinder() {
        AuthRequest request = validRequest();
        ImplRequest result = AuthContractBinder.authRequestBinder(request);
        assertNotNull(result);
        assertEquals(result.getUsername(),request.getUsername());
        assertEquals(result.getPassword(),request.getPassword());
    }

    public static AuthRequest validRequest(){
        return AuthRequest.builder()
                .username("Teste")
                .password("Senha")
                .build();
    }

    public static AuthModel validModel(){
        return AuthModel.builder()
                .name("Nome")
                .accessLevel(1)
                .clientId(1)
                .blocked(2)
                .token("Token")
                .build();
    }
}