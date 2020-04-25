package com.nexign.bootcamp.token_service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class UserTokenGenerationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("user");
        try (PrintWriter writer = resp.getWriter()) {
            if (username != null && !username.isEmpty()) {
                writer.println(String.format("{\"user\": \"%s\", \"token\": \"%s\"}",
                        username, UUID.nameUUIDFromBytes(username.getBytes()).toString()));
            } else {
                writer.println("{\"error\": \"Username is empty\"}");
                resp.setStatus(400);
                System.err.println("No username provided");
            }
        } catch (IOException ex) {
            resp.setStatus(500);
            System.err.println(String.format("Error printing result: %s", ex.getMessage()));
        }
    }
}
