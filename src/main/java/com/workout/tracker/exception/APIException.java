package com.workout.tracker.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class APIException extends RuntimeException{
    private final String userMessage;
    private final String developerMessage;
    private final HttpStatus httpStatus;
    private int httpErrorCode;

    public APIException(String userMessage, String developerMessage, HttpStatus httpStatus) {
        super(userMessage);
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
        this.httpStatus = httpStatus;
    }

    public APIException(String userMessage, String developerMessage, HttpStatus httpStatus, int httpErrorCode) {
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
        this.httpStatus = httpStatus;
        this.httpErrorCode = httpErrorCode;
    }
}
