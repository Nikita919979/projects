package com.epam.rd.edu.petProject.exception;

public abstract class AutobaseUncheckedException extends RuntimeException {
    public AutobaseUncheckedException() {
        super();
    }

    public AutobaseUncheckedException(String message) {
        super(message);
    }

    public AutobaseUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutobaseUncheckedException(Throwable cause) {
        super(cause);
    }

    protected AutobaseUncheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
