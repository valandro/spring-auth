package com.valandro.contract.exception;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;

public class ApiExceptionTest {
    private ApiException exception;

    @Before
    public void create_reference() {
        exception = new ApiException();
    }

    @Test
    public void construcor() {
        exception = new ApiException();
        assertNotNull(exception);
    }

    @Test
    public void test_constructor_with_args(){
        String message = "Internal Server Error";
        exception.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exception.setMessage(message);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void test_builder(){
        String message = "Bad Request";
        exception = ApiException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(message)
                .build();
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void test_build_to_string() {
        String message = "Bad Request";
        String exception = ApiException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(message)
                .build().toString();
        assertThat(exception, any(String.class));
    }

    @Test
    public void equals() {
        ApiException obj = new ApiException();
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
        assertTrue(exception.canEqual(new ApiException()));
    }
}