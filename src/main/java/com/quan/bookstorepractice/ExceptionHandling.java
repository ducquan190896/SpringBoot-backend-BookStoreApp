package com.quan.bookstorepractice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.quan.bookstorepractice.Exception.EntityNotFoundException;
import com.quan.bookstorepractice.Exception.ErrorResponse;

@ControllerAdvice
public class ExceptionHandling {
    
    @ExceptionHandler(EntityNotFoundException.class) 
    public ResponseEntity<Object> handleNotFoundException(EntityNotFoundException ex) {
            ErrorResponse err = new ErrorResponse(ex.getMessage(), ex, LocalDateTime.now());
            return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class) 
    public ResponseEntity<Object> handleArgumenteException(MethodArgumentNotValidException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage(), ex, LocalDateTime.now());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
