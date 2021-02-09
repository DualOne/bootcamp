package com.nexign.bootcamp.tokenservice.servlets;

import com.nexign.bootcamp.tokenservice.services.UserTokenService;

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
        String requestUsername = req.getParameter("user");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
            if (requestUsername != null && !requestUsername.isBlank()) {
                userTokenService.returnUserToken(requestUsername, resp, writer);
            } else {
                writer.println("{\"error\": \"Username is empty\"}");
                resp.setStatus(400);
            }
        } catch (IOException ex) {
            resp.setStatus(500);
        }
    }
}
