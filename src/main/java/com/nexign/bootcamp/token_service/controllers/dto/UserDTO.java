package com.nexign.bootcamp.token_service.controllers.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.StringJoiner;

public class UserDTO {

    private final String username;

    @JsonCreator
    public UserDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserDTO.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .toString();
    }
}
