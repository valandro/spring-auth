package com.valandro.impl.binder;

import com.valandro.impl.data.UserEntity;
import com.valandro.impl.model.AuthModel;
import com.valandro.impl.model.ImplModel;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthImplBinderTest {

    @Test
    public void constructor(){
        AuthImplBinder binder = new AuthImplBinder();
        assertNotNull(binder);
    }

    @Test
    public void bind_to_auth_model() {
        ImplModel model = validModel();
        String token = "Token";
        AuthModel result = AuthImplBinder.bindToAuthModel(model,token);
        assertEquals(model.getName(),result.getName());
        assertEquals(model.getClientId(),result.getClientId());
        assertEquals(model.getAccessLevel(),result.getAccessLevel());
        assertEquals(model.getBlocked(),result.getBlocked());
        assertEquals(token, result.getToken());
    }

    @Test
    public void bind_to_impl_model() {
        UserEntity entity = validEntity();
        ImplModel result = AuthImplBinder.bindToImplModel(entity);
        assertEquals(entity.getName(),result.getName());
        assertEquals(entity.getClientId(),result.getClientId());
        assertEquals(entity.getAccessLevel(),result.getAccessLevel());
        assertEquals(entity.getBlocked(),result.getBlocked());
    }

    public static ImplModel validModel(){
        return ImplModel.builder()
                .name("Nome")
                .accessLevel(1)
                .clientId(1)
                .blocked(2)
                .build();
    }

    public static UserEntity validEntity(){
        return UserEntity.builder()
                .id(1)
                .name("Nome")
                .password("Senha")
                .accessLevel(1)
                .blocked(2)
                .build();
    }
}