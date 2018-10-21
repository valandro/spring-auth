package com.valandro.contract.binder;

import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.response.AuthResponse;
import com.valandro.impl.model.ImplRequest;
import com.valandro.impl.model.UserModel;

public class AuthContractBinder {
    public static AuthResponse authResponseBinder(UserModel entity){
        return AuthResponse.builder()
                .token("Fixed token")
                .name(entity.getName())
                .accessLevel(entity.getAccessLevel())
                .build();
    }
    public static ImplRequest authRequestBinder(AuthRequest request){
        return ImplRequest.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }
}
