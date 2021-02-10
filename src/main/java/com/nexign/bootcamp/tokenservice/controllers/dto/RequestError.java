package com.nexign.bootcamp.tokenservice.controllers.dto;

import java.util.StringJoiner;

public class RequestError {

    private final String error;

    public RequestError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RequestError.class.getSimpleName() + "[", "]")
                .add("error='" + error + "'")
                .toString();
    }
}
