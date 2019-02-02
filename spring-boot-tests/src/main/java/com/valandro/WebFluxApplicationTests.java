package com.valandro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class WebFluxApplicationTests {

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    protected ObjectMapper objectMapper;
}
