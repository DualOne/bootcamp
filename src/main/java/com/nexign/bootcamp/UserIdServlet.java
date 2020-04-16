package com.nexign.bootcamp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("user");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
            if (username != null && !username.isEmpty()) {
                int userId = username.hashCode();
                writer.println(String.format("{\"user\": \"%s\", \"id\": %d}", username, userId));
            } else {
                writer.println("{\"error\": \"Username is empty\"}");
                System.err.println("No username provided");
            }
        } catch (IOException ex) {
            System.err.println(String.format("Error printing result: %s", ex.getMessage()));
        }
    }
}
