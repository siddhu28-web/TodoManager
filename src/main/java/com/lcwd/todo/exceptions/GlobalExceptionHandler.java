package com.lcwd.todo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger loggers = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //We create handler methods for specific exception
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex){
        loggers.info("Its Null Pointer Exception From Global Exception Handler Class");
        return new ResponseEntity<>("Null Pointer Exception generated" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    //Handling Resource Not Found Exception
    public ResponseEntity<ExceptionResponse> handlerResponseNotFoundExcepton(ResourceNotFoundException ex){
        loggers.error("ERROR : {}",ex.getMessage());
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setSuccess(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
