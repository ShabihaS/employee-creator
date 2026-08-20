package com.employeecreator.common;

import java.util.HashMap;
import com.employeecreator.common.dtos.ApiErrorResponse;
import com.employeecreator.common.exceptions.NotFoundException;
import com.employeecreator.common.exceptions.ServiceValidationException;
import com.employeecreator.common.exceptions.UnprocessableContentException;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException ex,
      HttpServletRequest req) {

    ApiErrorResponse response = ApiErrorResponse.of(
        HttpStatus.BAD_REQUEST,
        ex.getMessage(),
        req.getRequestURI());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleNotFoundException(
      NotFoundException ex,
      HttpServletRequest req) {

    ApiErrorResponse response = ApiErrorResponse.of(
        HttpStatus.NOT_FOUND,
        ex.getMessage(),
        req.getRequestURI());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UnprocessableContentException.class)
  public ResponseEntity<ApiErrorResponse> handleUnprocessableContentException(
      UnprocessableContentException ex,
      HttpServletRequest req) {

    ApiErrorResponse response = ApiErrorResponse.of(
        HttpStatus.UNPROCESSABLE_CONTENT,
        ex.getMessage(),
        req.getRequestURI());

    return new ResponseEntity<>(
        response,
        HttpStatus.UNPROCESSABLE_CONTENT);
  }

  @ExceptionHandler(ServiceValidationException.class)
  public ResponseEntity<ApiErrorResponse> handleServiceValidationException(
      ServiceValidationException ex,
      HttpServletRequest req) {

    ApiErrorResponse response = ApiErrorResponse.of(
        HttpStatus.UNPROCESSABLE_CONTENT,
        ex.getMessage(),
        req.getRequestURI(),
        ex.getErrors());

    return new ResponseEntity<>(
        response,
        HttpStatus.UNPROCESSABLE_CONTENT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorResponse> handleValidationException(
      MethodArgumentNotValidException ex,
      HttpServletRequest req) {

    ValidationErrors validationErrors = new ValidationErrors();

    ex.getBindingResult().getFieldErrors().forEach(error -> validationErrors.add(
        error.getField(),
        error.getDefaultMessage()));

    ApiErrorResponse response = ApiErrorResponse.of(
        HttpStatus.UNPROCESSABLE_CONTENT,
        "Validation Failed",
        req.getRequestURI(),
        new HashMap<>(validationErrors.getErrors()));

    return new ResponseEntity<>(
        response,
        HttpStatus.UNPROCESSABLE_CONTENT);
  }
}