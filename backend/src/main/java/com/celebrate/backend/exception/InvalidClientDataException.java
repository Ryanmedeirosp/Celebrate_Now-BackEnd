package com.celebrate.backend.exception;

public class InvalidClientDataException  extends RuntimeException {
    public InvalidClientDataException(String message) {
        super(message);
    }
}
