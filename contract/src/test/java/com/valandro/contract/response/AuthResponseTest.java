package com.valandro.contract.response;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;

public class AuthResponseTest {
    private AuthResponse response;

    @Before
    public void create_reference() {
        response = new AuthResponse();
    }

    @Test
    public void constructor() {
        AuthResponse constructor = new AuthResponse();
        assertNotNull(constructor);
    }

    @Test
    public void token() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
        response.setToken(token);
        assertNotNull(response.getToken());
        assertEquals(token, response.getToken());
    }

    @Test
    public void name() {
        String name = "Nome";
        response.setName(name);
        assertNotNull(response.getName());
        assertEquals(name, response.getName());
    }

    @Test
    public void access_level() {
        Integer accessLevel = 1;
        response.setAccessLevel(accessLevel);
        assertNotNull(response.getAccessLevel());
        assertEquals(accessLevel, response.getAccessLevel());
    }

    @Test
    public void equals() {
        AuthResponse obj = new AuthResponse();
        assertTrue(obj.equals(response));
    }

    @Test
    public void hash_code() {
        Integer hashCode = response.hashCode();
        assertNotNull(hashCode);
        assertThat(hashCode, any(Integer.class));
    }

    @Test
    public void can_equal() {
        assertTrue(response.canEqual(new AuthResponse()));
    }

    @Test
    public void to_string() {
        String result = response.toString();
        assertNotNull(result);
        assertThat(result, any(String.class));
    }
}