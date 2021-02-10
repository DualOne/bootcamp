package com.nexign.bootcamp.tokenservice.services;

import com.nexign.bootcamp.tokenservice.entities.NumberToken;
import com.nexign.bootcamp.tokenservice.exceptions.InvalidTokenFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("number-token")
public class NumberUserTokenService extends UserTokenService<NumberToken> {

    @Autowired
    public NumberUserTokenService(UserService userService) {
        super(userService);
    }

    @Override
    protected NumberToken getTokenByUsername(String username) {
        return new NumberToken(getUserToken(username));
    }

    @Override
    protected NumberToken convertToken(String token) throws InvalidTokenFormat {
        try {
            return new NumberToken(Integer.parseInt(token));
        } catch (NumberFormatException ex) {
            throw new InvalidTokenFormat(ex);
        }
    }

    private int getUserToken(String username) {
        return username.hashCode();
    }
}
