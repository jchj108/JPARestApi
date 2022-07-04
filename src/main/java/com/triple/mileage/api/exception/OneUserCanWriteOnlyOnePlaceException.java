package com.triple.mileage.api.exception;

public class OneUserCanWriteOnlyOnePlaceException extends RuntimeException {

    public OneUserCanWriteOnlyOnePlaceException() {
        super();
    }

    public OneUserCanWriteOnlyOnePlaceException(String message) {
        super(message);
    }

    public OneUserCanWriteOnlyOnePlaceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OneUserCanWriteOnlyOnePlaceException(Throwable cause) {
        super(cause);
    }
}
