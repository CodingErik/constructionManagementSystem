package com.company.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;


@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<com.company.constructionmanagementsystem.model.CustomErrorResponse> outOfRangeException(IllegalArgumentException e) {
        com.company.constructionmanagementsystem.model.CustomErrorResponse error = new com.company.constructionmanagementsystem.model.CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), e.getMessage());
        error.setStatus((HttpStatus.UNPROCESSABLE_ENTITY.value()));
        error.setTimestamp(LocalDateTime.now());
        ResponseEntity<com.company.constructionmanagementsystem.model.CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

}