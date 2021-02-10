package com.nexign.bootcamp.tokenservice.services;

import com.nexign.bootcamp.tokenservice.entities.Token;
import com.nexign.bootcamp.tokenservice.entities.User;
import com.nexign.bootcamp.tokenservice.exceptions.InvalidTokenFormat;

import java.util.Objects;
import java.util.Optional;

public abstract class UserTokenService<T extends Token<?>> {

    private final UserService userService;

    protected UserTokenService(UserService userService) {
        this.userService = userService;
    }

    public Optional<T> getToken(Long userId) {
        return userService.findById(userId)
                .map(User::getName)
                .map(this::getTokenByUsername);
    }

    public boolean isTokenValid(Long userId, String token) {
        return getToken(userId)
                .filter(userToken -> Objects.equals(convertToken(token), userToken))
                .isPresent();
    }

    protected abstract T convertToken(String token) throws InvalidTokenFormat;

    protected abstract T getTokenByUsername(String username);
}
