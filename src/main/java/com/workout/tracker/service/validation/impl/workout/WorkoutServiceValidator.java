package com.workout.tracker.service.validation.impl.workout;

import com.workout.tracker.model.workout.WorkoutBaseDTO;
import com.workout.tracker.repository.WorkoutRepository;
import com.workout.tracker.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class WorkoutServiceValidator implements Validator<WorkoutBaseDTO> {
    @Autowired
    protected WorkoutRepository workoutRepository;
}
