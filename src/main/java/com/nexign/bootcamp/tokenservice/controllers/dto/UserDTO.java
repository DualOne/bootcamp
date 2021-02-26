package com.nexign.bootcamp.tokenservice.controllers.dto;

import java.util.StringJoiner;

public class UserDTO {

    private String name;

    public UserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserDTO.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
