package com.nexign.bootcamp.token_service.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.UUID;

@Service
@Profile("string-token")
public class StringUserTokenService implements UserTokenService {

    @Override
    public void returnUserToken(String username, HttpServletResponse response, PrintWriter responseWriter) {
        responseWriter.println(String.format("{\"user\": \"%s\", \"token\": \"%s\"}", username, getUserToken(username)));
    }

    @Override
    public void validateUserToken(String username, String token, HttpServletResponse response, PrintWriter responseWriter) {
        if (token.equals(getUserToken(username))) {
            response.setStatus(204);
        } else {
            responseWriter.println("{\"error\": \"User token is invalid\"}");
            response.setStatus(409);
        }
    }

    private String getUserToken(String username) {
        return UUID.nameUUIDFromBytes(username.getBytes()).toString();
    }
}
