package com.vacinacerta.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessLogicException extends Exception{
    private String errorMessage;
    private HttpStatus statusCode;

    public BusinessLogicException(String errorMessage, HttpStatus statusCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }
}
