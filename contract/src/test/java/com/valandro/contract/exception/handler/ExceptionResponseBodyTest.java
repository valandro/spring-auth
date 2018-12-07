package com.valandro.contract.exception.handler;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;

public class ExceptionResponseBodyTest {
    private ExceptionResponseBody exception;

    @Before
    public void create_reference() {
        exception = new ExceptionResponseBody();
    }

    @Test
    public void construcor() {
        exception = new ExceptionResponseBody();
        assertNotNull(exception);
    }

    @Test
    public void test_constructor_with_args(){
        String message = "Internal Server Error";
        exception.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exception.setMessage(message);
        assertEquals(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), exception.getHttpStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void test_builder(){
        String message = "Bad Request";
        exception = ExceptionResponseBody.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .build();
        assertEquals(Integer.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getHttpStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void test_build_to_string() {
        String message = "Bad Request";
        String exception = ExceptionResponseBody.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .build().toString();
        assertThat(exception, any(String.class));
    }

    @Test
    public void equals() {
        ExceptionResponseBody obj = new ExceptionResponseBody();
        assertTrue(obj.equals(exception));
    }

    @Test
    public void hash_code() {
        Integer hashCode = exception.hashCode();
        assertNotNull(hashCode);
        assertThat(hashCode, any(Integer.class));
    }

    @Test
    public void can_equal() {
        assertTrue(exception.canEqual(new ExceptionResponseBody()));
    }


}