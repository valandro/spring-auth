package com.valandro.impl.binder;

import com.valandro.impl.data.UserEntity;
import com.valandro.impl.model.AuthModel;
import com.valandro.impl.model.ImplModel;

public class AuthImplBinder {
    public static AuthModel bindToAuthModel(ImplModel implModel, String token){
        return AuthModel.builder()
                .token(token)
                .clientId(implModel.getClientId())
                .name(implModel.getName())
                .accessLevel(implModel.getAccessLevel())
                .blocked(implModel.getBlocked())
                .build();
    }

    public static ImplModel bindToImplModel(UserEntity entity) {
        return ImplModel.builder()
                .name(entity.getName())
                .clientId(entity.getClientId())
                .accessLevel(entity.getAccessLevel())
                .blocked(entity.getBlocked())
                .build();
    }
}
