package com.nexign.bootcamp.tokenservice.services;

import com.nexign.bootcamp.tokenservice.entities.StringToken;
import com.nexign.bootcamp.tokenservice.exceptions.InvalidTokenFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Profile("string-token")
public class StringUserTokenService extends UserTokenService<StringToken> {

    @Autowired
    public StringUserTokenService(UserService userService) {
        super(userService);
    }

    @Override
    protected StringToken getTokenByUsername(String username) {
        return new StringToken(getUserToken(username));
    }

    @Override
    protected StringToken convertToken(String token) throws InvalidTokenFormat {
        return new StringToken(token);
    }

    private String getUserToken(String username) {
        return UUID.nameUUIDFromBytes(username.getBytes()).toString();
    }
}
