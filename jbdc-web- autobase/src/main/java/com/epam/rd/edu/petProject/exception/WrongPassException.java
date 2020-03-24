package com.epam.rd.edu.petProject.exception;

public class WrongPassException extends AutobaseUncheckedException {
    public WrongPassException() {
        super();
    }

    public WrongPassException(String message) {
        super(message);
    }

    public WrongPassException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPassException(Throwable cause) {
        super(cause);
    }

    protected WrongPassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
