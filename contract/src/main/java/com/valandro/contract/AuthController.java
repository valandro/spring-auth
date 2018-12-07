package com.valandro.contract;

import com.valandro.contract.facade.AuthFacade;
import com.valandro.contract.request.AuthRequest;
import com.valandro.contract.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {
    @Autowired
    private AuthFacade authFacade;

    @PostMapping(path = "/auth",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AuthResponse> authenticate(@RequestBody AuthRequest request){
        return authFacade.login(request);
    }
}
