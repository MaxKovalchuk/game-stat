package com.andersen.gamestat.exception.handler;

import com.andersen.gamestat.exception.GameStatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GameStatException.class)
    protected ResponseEntity handleMsClientException(GameStatException ex) {
        String message = ex.getMessage();
        HttpStatus status = ex.getHttpStatusCode();
        return ResponseEntity.status(status.value()).body(message);
    }

}
