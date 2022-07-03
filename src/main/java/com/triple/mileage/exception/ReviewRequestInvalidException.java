package com.triple.mileage.exception;

public class ReviewRequestInvalidException extends RuntimeException {

    public ReviewRequestInvalidException() {
        super();
    }

    public ReviewRequestInvalidException(String message) {
        super(message);
    }

    public ReviewRequestInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReviewRequestInvalidException(Throwable cause) {
        super(cause);
    }
}
