package com.nexign.bootcamp.token_service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.UUID;

public class UserTokenGenerationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String requestUsername = req.getParameter("user");
        try (PrintWriter writer = resp.getWriter()) {
            if (requestUsername != null && !requestUsername.isEmpty()) {
                generateUserToken(requestUsername, writer, resp);
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

    private void generateUserToken(String username, PrintWriter writer, HttpServletResponse resp) {
        try {
            switch (TokenMode.getActiveMode()) {
                case NUMBER:
                    writer.println(String.format("{\"user\": \"%s\", \"token\": \"%d\"}", username, getUserTokenAsNumber(username)));
                    break;
                case STRING:
                    writer.println(String.format("{\"user\": \"%s\", \"token\": \"%s\"}", username, getUserTokenAsString(username)));
            }
        } catch (IOException ex) {
            System.err.println("Error reading token mode from application.properties");
            resp.setStatus(500);
        }
    }

    public static String getUserTokenAsString(String username) {
        return UUID.nameUUIDFromBytes(username.getBytes()).toString();
    }

    public static int getUserTokenAsNumber(String username) {
        return username.hashCode();
    }

    public enum TokenMode {
        STRING, NUMBER;

        public static TokenMode getActiveMode() throws IOException {
            try (InputStream input = TokenMode.class.getClassLoader().getResourceAsStream("application.properties")) {
                Properties applicationProperties = new Properties();
                applicationProperties.load(input);
                return TokenMode.valueOf(applicationProperties.getProperty("tokenMode"));
            }
        }
    }
}
