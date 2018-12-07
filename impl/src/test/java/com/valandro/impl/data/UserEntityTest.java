package com.valandro.impl.data;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserEntityTest {
    private UserEntity entity;

    @Before
    public void create_reference() {
        entity = new UserEntity();
    }

    @Test
    public void constructor() {
        entity = new UserEntity();
        assertNotNull(entity);
    }

    @Test
    public void constructor_with_set_get() {
        entity.setAccessLevel(1);
        entity.setBlocked(1);
        entity.setId(1);
        entity.setClientId(1);
        entity.setName("Nome");
        entity.setPassword("Senha");

        assertThat(entity.getAccessLevel(), is(1));
        assertThat(entity.getBlocked(), is(1));
        assertThat(entity.getClientId(), is(1));
        assertThat(entity.getId(), is(1));
        assertThat(entity.getName(), is("Nome"));
        assertThat(entity.getPassword(), is("Senha"));
    }

    @Test
    public void test_builder(){
        entity = UserEntity.builder()
                           .clientId(1)
                           .accessLevel(1)
                           .blocked(1)
                           .id(1)
                           .name("Nome")
                           .password("Senha")
                           .build();

        assertThat(entity.getAccessLevel(), is(1));
        assertThat(entity.getBlocked(), is(1));
        assertThat(entity.getClientId(), is(1));
        assertThat(entity.getId(), is(1));
        assertThat(entity.getName(), is("Nome"));
        assertThat(entity.getPassword(), is("Senha"));
    }

    @Test
    public void test_build_to_string() {
        String entity = UserEntity.builder()
                                  .clientId(1)
                                  .accessLevel(1)
                                  .blocked(1)
                                  .id(1)
                                  .name("Nome")
                                  .password("Senha")
                                  .build().toString();

        assertThat(entity, any(String.class));
    }

    @Test
    public void equals() {
        UserEntity obj = new UserEntity();
        assertTrue(obj.equals(entity));
    }

    @Test
    public void hash_code() {
        Integer hashCode = entity.hashCode();
        assertNotNull(hashCode);
        assertThat(hashCode, any(Integer.class));
    }

    @Test
    public void can_equal() {
        assertTrue(entity.canEqual(new UserEntity()));
    }
}