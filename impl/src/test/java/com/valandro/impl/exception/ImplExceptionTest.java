package com.valandro.impl.exception;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;

public class ImplExceptionTest {
    private ImplException exception;

    @Before
    public void create_reference() {
        exception = new ImplException();
    }

    @Test
    public void construcor() {
        exception = new ImplException();
        assertNotNull(exception);
    }

    @Test
    public void test_constructor_with_args(){
        String message = "Bad Request";
        exception.setHttpStatus(HttpStatus.BAD_REQUEST);
        exception.setMessage(message);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void test_builder(){
        String message = "Bad Request";
        exception = ImplException.builder()
                                 .httpStatus(HttpStatus.BAD_REQUEST)
                                 .message(message)
                                 .build();
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void test_build_to_string() {
        String message = "Bad Request";
        String exception = ImplException.builder()
                                        .httpStatus(HttpStatus.BAD_REQUEST)
                                        .message(message)
                                        .build().toString();
        assertThat(exception, any(String.class));
    }

    @Test
    public void equals() {
        ImplException obj = new ImplException();
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
        assertTrue(exception.canEqual(new ImplException()));
    }

}