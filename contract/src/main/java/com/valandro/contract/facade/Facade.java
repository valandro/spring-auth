package com.valandro.contract.facade;

import com.valandro.impl.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Facade {
    @Autowired
    private AuthService authService;
}
