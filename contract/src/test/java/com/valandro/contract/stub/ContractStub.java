package com.valandro.contract.stub;

import com.valandro.contract.request.AuthRequest;
import com.valandro.impl.data.UserEntity;

public class ContractStub {
    public static AuthRequest getValidRequest() {
        return AuthRequest.builder()
                .password("Senha")
                .username("Nome")
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

    public static UserEntity notFoundEntity(){
        return null;
    }
}
