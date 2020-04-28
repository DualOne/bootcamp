package com.nexign.bootcamp.token_service.services;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public interface UserTokenService {

    void returnUserToken(String username, HttpServletResponse response, PrintWriter responseWriter);

    void validateUserToken(String username, String token, HttpServletResponse response, PrintWriter responseWriter);
}
