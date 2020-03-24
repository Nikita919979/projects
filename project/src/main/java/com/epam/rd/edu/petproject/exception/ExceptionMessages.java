package com.epam.rd.edu.petproject.exception;

import lombok.Getter;

public enum ExceptionMessages {
  WRONG_USER_DATA("Invalid user data"),
  LOGIN_EXIST("A user with this login exists"),
  EMAIL_EXIST("A user with this email exists"),
  BASE_ERROR("Error connecting to database"),
  USER_NOT_FOUND("User not found"),
  ENTITY_NOT_FOUND("Entity not found exception");

  @Getter
  private final String message;

  ExceptionMessages(String message) {
    this.message = message;
  }
}
