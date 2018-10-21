package com.valandro.impl.binder;

import com.valandro.impl.data.UserEntity;
import com.valandro.impl.model.UserModel;

public class AuthImplBinder {
    public static UserModel bindToUserModel(UserEntity entity){
        if(entity == null) return null;
        return UserModel.builder()
                .id(entity.getId())
                .clientId(entity.getClientId())
                .name(entity.getName())
                .accessLevel(entity.getAccessLevel())
                .blocked(entity.getBlocked())
                .build();
    }
}
