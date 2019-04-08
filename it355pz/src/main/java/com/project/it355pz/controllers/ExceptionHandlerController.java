package com.project.it355pz.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.it355pz.util.ShoppingException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
//	@ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) throws IOException {
//        return buildResponseEntity(new ShoppingException(HttpStatus.FORBIDDEN, e));
//    }
//
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) throws IOException {
//        return buildResponseEntity(new ShoppingException(HttpStatus.FORBIDDEN, e));
//    }
//
//    @ExceptionHandler(AuthenticationServiceException.class)
//    public ResponseEntity<?> handleAuthenticationServiceException(AuthenticationServiceException e) {
//        return buildResponseEntity(new ShoppingException(HttpStatus.UNAUTHORIZED, e));
//    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<?> handleEntityExistsException(EntityExistsException e) {
        return buildResponseEntity(new ShoppingException(HttpStatus.BAD_REQUEST, e));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleAuthenticationServiceException(EntityNotFoundException e) {
        return buildResponseEntity(new ShoppingException(HttpStatus.NOT_FOUND, e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        HashMap<String, List<String>> validationErrors = new HashMap<>();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            if (validationErrors.get(error.getField()) == null) {
                List<String> validationErrorMessages = new ArrayList<>();
                validationErrorMessages.add(error.getDefaultMessage());
                validationErrors.put(error.getField(), validationErrorMessages);
                continue;
            }

            validationErrors.get(error.getField()).add(error.getDefaultMessage());
        }

        return buildResponseEntity(new ShoppingException() {
            private static final long serialVersionUID = 1L;

            {
                setStatus(HttpStatus.BAD_REQUEST);
                setValidationErrors(validationErrors);
                setMessage("Input values are invalid.");
            }
        });
    }

    @ExceptionHandler(ShoppingException.class)
    public ResponseEntity<?> handleShoppingException(ShoppingException e) {
        return buildResponseEntity(e);
    }

    private ResponseEntity<?> buildResponseEntity(ShoppingException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
