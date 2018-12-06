package com.valandro.contract.request;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;

public class AuthRequestTest {

    private AuthRequest request;

    @Before
    public void create_reference() {
        request = new AuthRequest();
    }

    @Test
    public void constructor() {
        AuthRequest constructor = new AuthRequest();
        assertNotNull(constructor);
    }

    @Test
    public void name() {
        String name = "Nome";
        request.setUsername(name);
        assertNotNull(request.getUsername());
        assertEquals(name, request.getUsername());
    }

    @Test
    public void password() {
        String password = "Senha";
        request.setPassword(password);
        assertNotNull(request.getPassword());
        assertEquals(password, request.getPassword());
    }

    @Test
    public void equals() {
        AuthRequest obj = new AuthRequest();
        assertTrue(obj.equals(request));
    }

    @Test
    public void hash_code() {
        Integer hashCode = request.hashCode();
        assertNotNull(hashCode);
        assertThat(hashCode, any(Integer.class));
    }

    @Test
    public void can_equal() {
        assertTrue(request.canEqual(new AuthRequest()));
    }

    @Test
    public void to_string() {
        String result = request.toString();
        assertNotNull(result);
        assertThat(result, any(String.class));
    }
}