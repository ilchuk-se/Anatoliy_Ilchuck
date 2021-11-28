package com.epam.homework4.cinemawebapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        log.error("handleMethodArgumentNotValid: exception {}", ex.getMessage(), ex);
        return ex.getBindingResult().getAllErrors().stream()
                .map(err -> new Error(err.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleNullPointerException(NullPointerException ex) {
        log.error("handleNullPointerException: exception {}", ex.getMessage(), ex);
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleIndexOutOfBoundsException(IndexOutOfBoundsException ex){
        log.error("handleIndexOutOfBoundsException: exception {}", ex.getMessage(), ex);
        return new Error(ex.getMessage());
    }
}
