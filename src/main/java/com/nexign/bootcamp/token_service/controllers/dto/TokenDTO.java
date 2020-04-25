package com.nexign.bootcamp.token_service.controllers.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.StringJoiner;

public class TokenDTO {

    private final String token;

    @JsonCreator
    public TokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TokenDTO.class.getSimpleName() + "[", "]")
                .add("token='" + token + "'")
                .toString();
    }
}
