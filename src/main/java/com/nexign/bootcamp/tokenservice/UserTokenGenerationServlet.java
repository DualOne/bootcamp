package com.nexign.bootcamp.tokenservice;

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
        String requestUsername = req.getParameter("user");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
            if (requestUsername != null && !requestUsername.isBlank()) {
                generateUserToken(requestUsername, writer);
            } else {
                writer.println("{\"error\": \"Username is empty\"}");
                resp.setStatus(400);
            }
        } catch (IOException ex) {
            resp.setStatus(500);
        }
    }

    private void generateUserToken(String username, PrintWriter writer) throws IOException {
        switch (TokenMode.getActiveMode()) {
            case NUMBER -> writer.println(
                    String.format("{\"user\": \"%s\", \"token\": \"%d\"}", username, getUserTokenAsNumber(username)));
            case STRING -> writer.println(
                    String.format("{\"user\": \"%s\", \"token\": \"%s\"}", username, getUserTokenAsString(username)));
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
            InputStream configs = TokenMode.class.getClassLoader().getResourceAsStream("application.properties");
            try (configs) {
                Properties applicationProperties = new Properties();
                applicationProperties.load(configs);
                return TokenMode.valueOf(applicationProperties.getProperty("tokenMode"));
            }
        }
    }
}
