package com.nexign.bootcamp.token_service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserTokenValidationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestUsername = req.getParameter("user");
        String requestUserToken = req.getParameter("token");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
            if (requestUsername == null || requestUsername.isBlank()) {
                writer.println("{\"error\": \"Username is empty\"}");
                resp.setStatus(400);
            } else if (requestUserToken == null || requestUserToken.isBlank()) {
                writer.println("{\"error\": \"User token is empty\"}");
                resp.setStatus(400);
            } else {
                validateUserToken(requestUsername, requestUserToken, writer, resp);
            }
        } catch (IOException ex) {
            resp.setStatus(500);
        }
    }

    private void validateUserToken(String username,
                                   String token,
                                   PrintWriter writer,
                                   HttpServletResponse resp) throws IOException {
        try {
            boolean isTokenValid = switch (UserTokenGenerationServlet.TokenMode.getActiveMode()) {
                case NUMBER -> Integer.parseInt(token) == UserTokenGenerationServlet.getUserTokenAsNumber(username);
                case STRING -> token.equals(UserTokenGenerationServlet.getUserTokenAsString(username));
            };

            if (isTokenValid) {
                resp.setStatus(204);
            } else {
                writer.println("{\"error\": \"User token is invalid\"}");
                resp.setStatus(409);
            }
        } catch (NumberFormatException ex) {
            writer.println("{\"error\": \"Invalid token format\"}");
            resp.setStatus(422);
        }
    }
}
