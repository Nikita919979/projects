package com.epam.rd.edu.petProject.exception;

public class WrongFNameException extends AutobaseUncheckedException {
    public WrongFNameException() {
        super();
    }

    public WrongFNameException(String message) {
        super(message);
    }

    public WrongFNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongFNameException(Throwable cause) {
        super(cause);
    }

    protected WrongFNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
