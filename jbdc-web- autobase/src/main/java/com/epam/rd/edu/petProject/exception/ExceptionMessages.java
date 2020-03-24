package com.epam.rd.edu.petProject.exception;

import lombok.Getter;

public enum ExceptionMessages {
    DEFAULT_EXCEPTION("Autobase error"),
    WRONG_PASS("Pass isn`t valid"),
    WRONG_LOGIN("Login isn`t valid"),
    WRONG_EMAIL("Email isn`t valid"),
    WRONG_NAME("Name isn`t valid"),
    WRONG_FAMILY_NAME("Family isn`t valid"),
    LOGIN_EXIST("A user with this login exists"),
    EMAIL_EXIST("A user with this email exists"),
    BASE_ERROR("Error connecting to database"),
    FRONT_COMMAND_ERROR("Error working with object");

    @Getter
    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }
}
