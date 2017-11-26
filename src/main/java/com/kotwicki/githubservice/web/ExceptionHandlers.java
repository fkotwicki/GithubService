package com.kotwicki.githubservice.web;

import com.kotwicki.githubservice.api.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExceptionHandlers {

    @ExceptionHandler
    ResponseEntity handle(final RepositoryNotFoundException exc) {
        return buildErrorResponseEntity(HttpStatus.NOT_FOUND, "Repository " + exc.getRepositoryName() + " not found.");
    }

    @ExceptionHandler
    ResponseEntity handle(final ApiException exc) {
        return buildErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exc.getCause().getMessage());
    }

    private ResponseEntity buildErrorResponseEntity(final HttpStatus status, final String message) {
        return ResponseEntity.status(status).body(new ErrorResponse(message));
    }
}
