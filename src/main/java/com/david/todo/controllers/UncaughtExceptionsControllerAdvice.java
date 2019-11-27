package com.david.todo.controllers;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = { RestController.class })
public class UncaughtExceptionsControllerAdvice {
  @ExceptionHandler({ MethodArgumentNotValidException.class, HttpMessageNotReadableException.class,
      ConstraintViolationException.class })
  public ResponseEntity<MyResponse<String>> handleBindingErrors(Exception ex) {
    // do whatever you want with the exceptions
    MyResponse<String> mr = new MyResponse<String>(ex.getMessage(), HttpStatus.BAD_REQUEST, "Bad Request");
    return new ResponseEntity<MyResponse<String>>(mr, HttpStatus.BAD_REQUEST);
  }
}