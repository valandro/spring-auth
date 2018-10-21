package com.valandro.contract.exception.handler;

import com.valandro.contract.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Object> handleEntityNotFound(ApiException ex){
        ExceptionResponseBody exception = new ExceptionResponseBody(ex.getHttpStatus().value(),ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(exception);
    }
}
