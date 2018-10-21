package com.valandro.impl.repository;

import com.valandro.impl.data.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByNameAndPassword(String name, String password);
}
