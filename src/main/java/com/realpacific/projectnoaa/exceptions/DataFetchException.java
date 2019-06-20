package com.realpacific.projectnoaa.exceptions;

public class DataFetchException extends RuntimeException {
    public DataFetchException(String message) {
        super(message);
    }
}
