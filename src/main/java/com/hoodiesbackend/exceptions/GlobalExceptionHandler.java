package com.hoodiesbackend.exceptions;

import com.hoodiesbackend.entities.response.Response;
import com.hoodiesbackend.entities.response.ResponseHandler;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        return ResponseHandler.fail(HttpStatus.BAD_REQUEST, ex.getCause().getMessage());
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<Response> handlerBadRequest(PasswordException ex) {
        return ResponseHandler.fail(HttpStatus.BAD_REQUEST, ex.getCause().getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handlerBadRequest(MethodArgumentNotValidException ex) {
        return ResponseHandler.fail(HttpStatus.BAD_REQUEST, ex.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Response> handlerInternalServerError(UnexpectedTypeException ex) {
        return ResponseHandler.fail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response> handlerInternalServerError(DataIntegrityViolationException ex) {
        return ResponseHandler.fail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<Response> handlerInternalServerError(CartException ex) {
        return ResponseHandler.fail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
