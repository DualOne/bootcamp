package com.nexign.bootcamp.tokenservice;

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
            if (username != null && !username.isBlank()) {
                writer.println(String.format("{\"user\": \"%s\", \"token\": \"%s\"}",
                        username, getUserToken(username)));
            } else {
                writer.println("{\"error\": \"Username is empty\"}");
                resp.setStatus(400);
            }
        } catch (IOException ex) {
            resp.setStatus(500);
        }
    }

    private String getUserToken(String username) {
        return UUID.nameUUIDFromBytes(username.getBytes()).toString();
    }
}
