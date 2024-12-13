package com.workout.tracker.exception.handler;

import com.workout.tracker.exception.APIException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleAPIException(APIException apiException) {
        int httpErrorCode = apiException.getHttpErrorCode();
        Map<String, String> response = new LinkedHashMap<>();
        response.put("userMessage", apiException.getUserMessage());
        response.put("developerMessage", apiException.getDeveloperMessage());
        response.put("errorCode", httpErrorCode > 0 ? String.valueOf(httpErrorCode) : apiException.getHttpStatus().value() + "01");
        return new ResponseEntity<>(
                response,
                apiException.getHttpStatus()
        );
    }
}
