package ru.surpavel.bugtrackingsystem.service;

public class ResourceNotEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final String NOT_EMPTY = " not empty.";

    public ResourceNotEmptyException() {
        super();
    }

    public ResourceNotEmptyException(String message) {
        super(message + NOT_EMPTY);
    }

    public ResourceNotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
