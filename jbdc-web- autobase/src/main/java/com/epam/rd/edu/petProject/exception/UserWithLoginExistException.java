package com.epam.rd.edu.petProject.exception;

public class UserWithLoginExistException extends AutobaseUncheckedException {
    public UserWithLoginExistException() {
        super();
    }

    public UserWithLoginExistException(String message) {
        super(message);
    }

    public UserWithLoginExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWithLoginExistException(Throwable cause) {
        super(cause);
    }

    protected UserWithLoginExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
