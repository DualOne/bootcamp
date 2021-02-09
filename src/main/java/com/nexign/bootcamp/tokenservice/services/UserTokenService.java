package com.nexign.bootcamp.tokenservice.services;

import com.nexign.bootcamp.tokenservice.entities.Token;
import com.nexign.bootcamp.tokenservice.exceptions.InvalidTokenFormat;

import java.util.Objects;

public interface UserTokenService<T extends Token<?>> {

    T getToken(String username);

    T convertToken(String token) throws InvalidTokenFormat;

    default boolean isTokenValid(String username, String token) {
        return Objects.equals(getToken(username), convertToken(token));
    }
}
