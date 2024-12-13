package com.workout.tracker.service.validation;


public interface Validator<T> {
    void validate(T validator);
}
