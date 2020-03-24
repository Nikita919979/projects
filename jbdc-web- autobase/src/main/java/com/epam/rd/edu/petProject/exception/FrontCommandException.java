package com.epam.rd.edu.petProject.exception;

public class FrontCommandException extends AutobaseUncheckedException{
    public FrontCommandException() {
        super();
    }

    public FrontCommandException(String message) {
        super(message);
    }

    public FrontCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrontCommandException(Throwable cause) {
        super(cause);
    }

    protected FrontCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
