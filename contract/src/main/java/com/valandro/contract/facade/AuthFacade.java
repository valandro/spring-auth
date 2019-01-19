package com.valandro.contract.facade;

import com.valandro.contract.binder.AuthContractBinder;
import com.valandro.contract.exception.ApiException;
import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.response.AuthResponse;
import com.valandro.impl.binder.AuthImplBinder;
import com.valandro.impl.data.UserEntity;
import com.valandro.impl.model.ImplRequest;
import com.valandro.impl.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthFacade {
    @Autowired
    private AuthService authService;

    public Mono<AuthResponse> login(AuthRequest request) {
        return Mono.just(request)
                .map(AuthContractBinder::authRequestBinder)
                .map(this::findUserByNameAndPassword)
                .map(AuthImplBinder::bindToImplModel)
                .map(this.authService::createAuthModel)
                .map(AuthContractBinder::authResponseBinder);
    }

    private UserEntity findUserByNameAndPassword(ImplRequest request) {
        return this.authService.findUserByNameAndPassword(request)
                .orElseThrow(() -> {
                    throw new ApiException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
                });
    }
}
