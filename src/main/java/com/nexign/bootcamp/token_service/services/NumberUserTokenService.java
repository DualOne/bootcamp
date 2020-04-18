package com.nexign.bootcamp.token_service.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Service
@Profile("number-token")
public class NumberUserTokenService implements UserTokenService {

    @Override
    public void returnUserToken(String username, HttpServletResponse response, PrintWriter responseWriter) {
        responseWriter.println(String.format("{\"user\": \"%s\", \"token\": \"%d\"}", username, getUserToken(username)));
    }

    @Override
    public void validateUserToken(String username, String token, HttpServletResponse response, PrintWriter responseWriter) {
        try {
            if (Integer.parseInt(token) == getUserToken(username)) {
                response.setStatus(204);
            } else {
                responseWriter.println("{\"error\": \"User token is invalid\"}");
                response.setStatus(409);
            }
        } catch (NumberFormatException ex) {
            responseWriter.println("{\"error\": \"User token is invalid\"}");
            response.setStatus(422);
        }
    }

    private int getUserToken(String username) {
        return username.hashCode();
    }
}
