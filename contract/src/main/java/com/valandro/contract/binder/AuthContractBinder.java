package com.valandro.contract.binder;

import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.response.AuthResponse;
import com.valandro.impl.model.ImplRequest;
import com.valandro.impl.model.AuthModel;

public class AuthContractBinder {
    public static AuthResponse authResponseBinder(AuthModel entity){
        return AuthResponse.builder()
                .token(entity.getToken())
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
