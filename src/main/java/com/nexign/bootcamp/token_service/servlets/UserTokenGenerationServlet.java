package com.nexign.bootcamp.token_service.servlets;

import com.nexign.bootcamp.token_service.services.UserTokenService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserTokenGenerationServlet extends HttpServlet {

    private final UserTokenService userTokenService;

    public UserTokenGenerationServlet(UserTokenService userTokenService) {
        this.userTokenService = userTokenService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String requestUsername = req.getParameter("user");
        try (PrintWriter writer = resp.getWriter()) {
            if (requestUsername != null && !requestUsername.isEmpty()) {
                userTokenService.returnUserToken(requestUsername, resp, writer);
            } else {
                writer.println("{\"error\": \"Username is empty\"}");
                resp.setStatus(400);
                System.err.println("No username provided");
            }
        } catch (IOException ex) {
            System.err.println(String.format("Error printing result: %s", ex.getMessage()));
            resp.setStatus(500);
        }
    }
}
