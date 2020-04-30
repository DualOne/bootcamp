package com.nexign.bootcamp.token_service.services;

import com.nexign.bootcamp.token_service.entities.Token;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Yaroslav.Zakharenko
 * @since 30.04.2020 18:37
 */
@Service
public class UserTokenService {

    public Token getToken(String username) {
        return getUserToken(username);
    }

    public boolean isTokenValid(String username, Token token) {
        return Objects.equals(token, getUserToken(username));
    }

    private Token getUserToken(String username) {
        return new Token(UUID.nameUUIDFromBytes(username.getBytes()).toString());
    }
}
