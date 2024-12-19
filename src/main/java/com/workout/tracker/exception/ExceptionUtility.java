package com.workout.tracker.exception;

import org.springframework.http.HttpStatus;

public class ExceptionUtility {
    public static APIException noDataAvailable() {
        return new APIException(
                "No data available",
                "No data available",
                HttpStatus.NOT_FOUND
        );
    }
    public static APIException invalidWorkoutId() {
        return new APIException(
                "Provide valid workout id",
                "Provide valid workout id",
                HttpStatus.NOT_FOUND
        );
    }

    public static APIException invalidUserId() {
        return new APIException(
                "Provide valid user id",
                "Provide valid user id",
                HttpStatus.NOT_FOUND
        );
    }
}
