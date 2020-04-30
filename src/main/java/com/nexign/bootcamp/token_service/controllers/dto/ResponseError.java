package com.nexign.bootcamp.token_service.controllers.dto;

import java.util.StringJoiner;

/**
 * @author Yaroslav.Zakharenko
 * @since 30.04.2020 18:48
 */
public class ResponseError {

    private String error;

    public ResponseError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ResponseError.class.getSimpleName() + "[", "]")
                .add("error='" + error + "'")
                .toString();
    }
}
