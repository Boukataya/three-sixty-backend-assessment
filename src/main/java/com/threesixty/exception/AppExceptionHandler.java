package com.threesixty.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.UnexpectedTypeException;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ListingNotFoundException.class)
    public ResponseEntity<Object> handleListingNotFound(ListingNotFoundException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, new Date()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DealerNotFoundException.class)
    public ResponseEntity<Object> handleListingNotFound(DealerNotFoundException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, new Date()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TierLimitExceeption.class)
    public ResponseEntity<Object> handleListingNotFound(TierLimitExceeption ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.FORBIDDEN, new Date()), HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND, new Date()), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("message", ex.getLocalizedMessage());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, UnexpectedTypeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getLocalizedMessage());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
