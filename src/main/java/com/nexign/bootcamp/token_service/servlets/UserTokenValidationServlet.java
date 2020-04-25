package com.nexign.bootcamp.token_service.servlets;

import com.nexign.bootcamp.token_service.services.UserTokenService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserTokenValidationServlet extends HttpServlet {

    private final UserTokenService userTokenService;

    public UserTokenValidationServlet(UserTokenService userTokenService) {
        this.userTokenService = userTokenService;
    }

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
                userTokenService.validateUserToken(requestUsername, requestUserToken, resp, writer);
            }
        } catch (IOException ex) {
            System.err.println(String.format("Error printing result: %s", ex.getMessage()));
            resp.setStatus(500);
        }
    }
}
