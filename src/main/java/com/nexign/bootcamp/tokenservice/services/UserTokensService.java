package com.nexign.bootcamp.tokenservice.services;

import com.nexign.bootcamp.tokenservice.entities.Token;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserTokensService {

    public Token create(String username) {
        return new Token(UUID.nameUUIDFromBytes(username.getBytes()).toString());
    }

    public boolean validate(String username, Token validatedToken) {
        return Objects.equals(create(username), validatedToken);
    }
}
