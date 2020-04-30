package com.nexign.bootcamp.token_service.entities;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Yaroslav.Zakharenko
 * @since 30.04.2020 18:40
 */
public class Token {

    private String token;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(token, token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Token.class.getSimpleName() + "[", "]")
                .add("token='" + token + "'")
                .toString();
    }
}
