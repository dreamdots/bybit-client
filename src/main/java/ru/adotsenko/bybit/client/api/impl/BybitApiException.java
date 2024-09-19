package ru.adotsenko.bybit.client.api.impl;

public class BybitApiException extends RuntimeException {

    public BybitApiException() {
    }

    public BybitApiException(String message) {
        super(message);
    }

    public BybitApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BybitApiException(Throwable cause) {
        super(cause);
    }
}
