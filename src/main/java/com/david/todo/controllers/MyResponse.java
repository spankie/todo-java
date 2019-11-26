package com.david.todo.controllers;

import org.springframework.http.HttpStatus;

/**
 * MyResponse<T> */
public class MyResponse<T>{

  private T data;
  private HttpStatus status;
  private String message;

  MyResponse(T data, HttpStatus status, String message) {
    this.data = data;
    this.status = status;
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


}