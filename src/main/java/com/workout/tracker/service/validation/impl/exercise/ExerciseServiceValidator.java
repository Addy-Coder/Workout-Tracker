package com.workout.tracker.service.validation.impl.exercise;

import com.workout.tracker.entity.Exercise;
import com.workout.tracker.model.exercise.ExerciseBaseDTO;
import com.workout.tracker.repository.ExerciseRepository;
import com.workout.tracker.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ExerciseServiceValidator implements Validator<ExerciseBaseDTO> {
    @Autowired
    protected ExerciseRepository exerciseRepository;
}
