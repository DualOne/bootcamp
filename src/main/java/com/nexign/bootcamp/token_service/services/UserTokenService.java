package com.nexign.bootcamp.token_service.services;

import com.nexign.bootcamp.token_service.entities.Token;
import com.nexign.bootcamp.token_service.exceptions.InvalidTokenFormat;

import java.util.Objects;

public interface UserTokenService<T extends Token<?>> {

    T getToken(String username);

    T convertToken(String token) throws InvalidTokenFormat;

    default boolean isTokenValid(String username, String token) {
        return Objects.equals(getToken(username), convertToken(token));
    }
}
