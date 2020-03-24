package com.epam.rd.edu.petProject.exception;

public class UserWithEmailExistException extends AutobaseUncheckedException {
    public UserWithEmailExistException() {
        super();
    }

    public UserWithEmailExistException(String message) {
        super(message);
    }

    public UserWithEmailExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWithEmailExistException(Throwable cause) {
        super(cause);
    }

    protected UserWithEmailExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
