package com.epam.rd.edu.petproject.exception;

public class WrongUserDataException extends AutobaseUncheckedException {

  public WrongUserDataException() {
    super();
  }

  public WrongUserDataException(String message) {
    super(message);
  }

  public WrongUserDataException(String message, Throwable cause) {
    super(message, cause);
  }

  public WrongUserDataException(Throwable cause) {
    super(cause);
  }

  protected WrongUserDataException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
