package com.nexign.bootcamp.tokenservice.controllers.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.nexign.bootcamp.tokenservice.entities.Token;

import java.util.StringJoiner;

public class TokenDTO {

    private final String token;

    @JsonCreator
    public TokenDTO(String token) {
        this.token = token;
    }

    public static TokenDTO of(Token<?> token) {
        return new TokenDTO(String.valueOf(token.getValue()));
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
