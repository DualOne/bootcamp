package com.nexign.bootcamp.token_service.controllers.dto;

import java.util.StringJoiner;

/**
 * @author Yaroslav.Zakharenko
 * @since 30.04.2020 19:21
 */
public class UserDTO {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserDTO.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .toString();
    }
}
