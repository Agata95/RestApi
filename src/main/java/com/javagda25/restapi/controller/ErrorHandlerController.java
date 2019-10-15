package com.javagda25.restapi.controller;

import com.javagda25.restapi.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleEntityNotFound(EntityNotFoundException exception) {
        return ResponseEntity
        .status(HttpStatus.BAD_REQUEST) // 400
        .body(new ErrorMessage("Unable to find entity " + exception.getMessage()));
    }
}
