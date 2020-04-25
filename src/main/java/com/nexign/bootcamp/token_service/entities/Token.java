package com.nexign.bootcamp.token_service.entities;

import java.util.Objects;

public abstract class Token<T> {

    private final T token;

    public Token(T token) {
        this.token = token;
    }

    public T getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token<?> other = (Token<?>) o;
        return Objects.equals(token, other.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
