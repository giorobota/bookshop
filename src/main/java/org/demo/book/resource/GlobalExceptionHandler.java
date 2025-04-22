package org.demo.book.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoSuchElementException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse("NOT_FOUND", ex.getMessage(), Instant.now()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse("BAD_REQUEST", ex.getMessage(), Instant.now()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllOtherExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse("INTERNAL_ERROR", ex.getMessage(), Instant.now()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

