package com.nexign.bootcamp.token_service.services;

import com.nexign.bootcamp.token_service.entities.StringToken;
import com.nexign.bootcamp.token_service.exceptions.InvalidTokenFormat;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Profile("string-token")
public class StringUserTokenService implements UserTokenService<StringToken> {

    @Override
    public StringToken getToken(String username) {
        return new StringToken(getUserToken(username));
    }

    @Override
    public StringToken convertToken(String token) throws InvalidTokenFormat {
        return new StringToken(token);
    }

    private String getUserToken(String username) {
        return UUID.nameUUIDFromBytes(username.getBytes()).toString();
    }
}
