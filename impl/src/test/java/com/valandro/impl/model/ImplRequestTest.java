package com.valandro.impl.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;

public class ImplRequestTest {
    private ImplRequest model;

    @Before
    public void create_reference() {
        model = new ImplRequest();
    }

    @Test
    public void name() {
        String name = "Nome";
        model.setUsername(name);
        assertNotNull(model.getUsername());
        assertEquals(name, model.getUsername());
    }

    @Test
    public void password() {
        String password = "Senha";
        model.setPassword(password);
        assertNotNull(model.getPassword());
        assertEquals(password, model.getPassword());
    }

    @Test
    public void equals() {
        ImplRequest obj = new ImplRequest();
        assertTrue(obj.equals(model));
    }

    @Test
    public void hash_code() {
        Integer hashCode = model.hashCode();
        assertNotNull(hashCode);
        assertThat(hashCode, any(Integer.class));
    }

    @Test
    public void can_equal() {
        assertTrue(model.canEqual(new ImplRequest()));
    }

    @Test
    public void to_string() {
        String result = model.toString();
        assertNotNull(result);
        assertThat(result, any(String.class));
    }

}