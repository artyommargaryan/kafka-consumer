package com.kafka.exceptions;

public class FieldsAreNotValidException extends RuntimeException {
    public FieldsAreNotValidException(String message) {
        super(message);
    }
}
