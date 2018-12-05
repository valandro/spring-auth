package com.valandro.impl.stub;

import com.valandro.impl.data.UserEntity;
import com.valandro.impl.model.ImplModel;
import com.valandro.impl.model.ImplRequest;

public class ImplStub {
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

    public static ImplRequest validImplRequest() {
        return ImplRequest.builder()
                .username("Nome")
                .password("Senha")
                .build();
    }

    public static ImplModel validImplModel() {
        return ImplModel.builder()
                .name("Nome")
                .clientId(1)
                .blocked(1)
                .accessLevel(2)
                .build();
    }
}
