package com.triple.mileage.exception;

public class InvalidReviewRequestException extends RuntimeException {

    public InvalidReviewRequestException() {
        super();
    }

    public InvalidReviewRequestException(String message) {
        super(message);
    }

    public InvalidReviewRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReviewRequestException(Throwable cause) {
        super(cause);
    }
}
