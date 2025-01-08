package com.hoodiesbackend.exceptions;

import com.hoodiesbackend.entities.response.Response;
import com.hoodiesbackend.entities.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    //UNDER CONSTRUCTION
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handlerNotFound(NotFoundException ex) {
        return ResponseHandler.fail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> handlerBadRequest(BadRequestException ex) {
        return ResponseHandler.fail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handlerBadRequest(HttpMessageNotReadableException ex) {
        return ResponseHandler.fail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
