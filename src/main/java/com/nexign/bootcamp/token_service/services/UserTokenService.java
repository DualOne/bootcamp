package com.nexign.bootcamp.token_service.services;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Yaroslav.Zakharenko
 * @since 19.04.2020 1:17
 */
public interface UserTokenService {

    void returnUserToken(String username, HttpServletResponse response, PrintWriter responseWriter);

    void validateUserToken(String username, String token, HttpServletResponse response, PrintWriter responseWriter);
}
