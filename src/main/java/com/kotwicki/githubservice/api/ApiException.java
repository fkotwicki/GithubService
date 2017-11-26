package com.kotwicki.githubservice.api;

public class ApiException extends RuntimeException {

    ApiException(final Throwable throwable) {
        super(throwable);
    }
}
