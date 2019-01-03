package com.valandro.impl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valandro.impl.binder.AuthImplBinder;
import com.valandro.impl.data.UserEntity;
import com.valandro.impl.exception.ImplException;
import com.valandro.impl.model.ImplModel;
import com.valandro.impl.model.ImplRequest;
import com.valandro.impl.model.AuthModel;
import com.valandro.impl.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Value("${security.session-time-in-hours}")
    private Integer sessionTime;
    @Value("${security.jwt-key}")
    private String securityKey;
    @Autowired
    private ObjectMapper objectMapper;

    public AuthModel createAuthModel(ImplModel implModel){
        return AuthImplBinder.bindToAuthModel(implModel, this.generateToken(implModel));
    }

    public String generateToken(ImplModel implModel) {
        try {
            return this.createToken(implModel);
        } catch (JsonProcessingException e) {
            throw new ImplException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Optional<UserEntity> findUserByNameAndPassword(ImplRequest request) {
        return Optional.ofNullable(
                this.userRepository
                        .findByNameAndPassword(request.getUsername(),
                                               hashPassword(request.getPassword()))
        );
    }


    private String createToken(ImplModel authModel) throws JsonProcessingException {
        Date expiration = Date.from(ZonedDateTime.now(ZoneOffset.UTC).plusHours(this.sessionTime).toInstant());
        return Jwts.builder()
                   .setSubject(this.objectMapper.writeValueAsString(authModel))
                   .signWith(SignatureAlgorithm.HS512, this.securityKey)
                   .setExpiration(expiration)
                   .compact();
    }

    public String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
