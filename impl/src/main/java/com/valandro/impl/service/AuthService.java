package com.valandro.impl.service;

import com.valandro.impl.binder.AuthImplBinder;
import com.valandro.impl.data.UserEntity;
import com.valandro.impl.model.ImplRequest;
import com.valandro.impl.model.UserModel;
import com.valandro.impl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public Optional<UserModel> findUser(ImplRequest request){
        UserEntity entity = this.findUserByNameAndPassword(request);
        return Optional.ofNullable(AuthImplBinder.bindToUserModel(entity));
    }

    private UserEntity findUserByNameAndPassword(ImplRequest request) {
        return this.userRepository.
                findByNameAndPassword(
                        request.getUsername(),
                        request.getPassword());
    }
}
