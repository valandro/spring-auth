package com.valandro.impl.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;

public class ImplModelTest {

    private ImplModel model;

    @Before
    public void create_reference() {
        model = new ImplModel();
    }

    @Test
    public void constructor() {
        ImplModel result = new ImplModel();
        assertNotNull(result);
    }

    @Test
    public void client_id() {
        Integer id = 1;
        model.setClientId(id);
        assertNotNull(model.getClientId());
        assertEquals(id, model.getClientId());
    }

    @Test
    public void access_level() {
        Integer accessLevel = 1;
        model.setAccessLevel(accessLevel);
        assertNotNull(model.getAccessLevel());
        assertEquals(accessLevel, model.getAccessLevel());
    }

    @Test
    public void name() {
        String name = "Nome";
        model.setName(name);
        assertNotNull(model.getName());
        assertEquals(name, model.getName());
    }

    @Test
    public void blocked() {
        Integer blocked = 1;
        model.setBlocked(blocked);
        assertNotNull(model.getBlocked());
        assertEquals(blocked, model.getBlocked());
    }


    @Test
    public void equals() {
        ImplModel obj = new ImplModel();
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
        assertTrue(model.canEqual(new ImplModel()));
    }

    @Test
    public void to_string() {
        String result = model.toString();
        assertNotNull(result);
        assertThat(result, any(String.class));
    }
}