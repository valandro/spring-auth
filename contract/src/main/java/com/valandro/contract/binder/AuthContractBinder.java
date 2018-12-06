package com.valandro.contract.binder;

import com.valandro.contract.request.AuthRequest;
import com.valandro.impl.model.ImplRequest;
import com.valandro.impl.model.AuthModel;

public class AuthContractBinder {
    public static com.valandro.contract.response.AuthResponse authResponseBinder(AuthModel entity){
        return com.valandro.contract.response.AuthResponse.builder()
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
