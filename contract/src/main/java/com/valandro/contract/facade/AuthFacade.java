package com.valandro.contract.facade;

import com.valandro.contract.binder.AuthContractBinder;
import com.valandro.contract.exception.ApiException;
import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.response.AuthResponse;
import com.valandro.impl.model.ImplRequest;
import com.valandro.impl.model.UserModel;
import com.valandro.impl.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthFacade {
    @Autowired
    private AuthService authService;

    public AuthResponse login(AuthRequest request) {
        ImplRequest implRequest = AuthContractBinder.authRequestBinder(request);
        UserModel response = this.authService.findUser(implRequest)
                                .orElseThrow(()-> {
                                    throw new ApiException(
                                            HttpStatus.BAD_REQUEST,
                                            "Usuário não encontrado.");
                                });
        return AuthContractBinder.authResponseBinder(response);
    }
}
