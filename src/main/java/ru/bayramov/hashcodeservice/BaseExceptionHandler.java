package ru.bayramov.hashcodeservice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgument(UnsupportedOperationException exception) {
        return new ResponseEntity<>(new ErrorMessage(exception.getLocalizedMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @RequiredArgsConstructor
    private final static class ErrorMessage {
        private final String message;
    }
}
