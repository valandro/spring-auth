package com.valandro.contract.stub;

import com.valandro.contract.AuthController;
import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.response.AuthResponse;
import com.valandro.impl.data.UserEntity;

public class ContractStub {
    public static AuthRequest getValidRequest() {
        return AuthRequest.builder()
                .username("Lucas")
                .password("Lucas")
                .build();
    }

    public static AuthRequest getInvalidRequest() {
        return AuthRequest.builder()
                .username("Test")
                .password("Jose")
                .build();
    }

    public static UserEntity validEntity() {
        return UserEntity.builder()
                .id(1)
                .name("Nome")
                .password("Senha")
                .blocked(1)
                .accessLevel(1)
                .clientId(1)
                .build();
    }

    public static UserEntity notFoundEntity() {
        return null;
    }

    public static AuthResponse getValidResponse() {
        return AuthResponse.builder()
                .name("Nome")
                .accessLevel(1)
                .token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
                .build();
    }
}
