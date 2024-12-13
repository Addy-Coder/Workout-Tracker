package com.workout.tracker.service.validation.impl.workout;

import com.workout.tracker.entity.Workout;
import com.workout.tracker.exception.APIException;
import com.workout.tracker.model.workout.WorkoutBaseDTO;
import com.workout.tracker.utility.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CreateWorkoutValidator extends WorkoutServiceValidator {
    @Override
    public void validate(WorkoutBaseDTO validator) {

        String workoutName = validator.getName();
        if (Utility.isStringEmptyOrNull(workoutName)) {
            throw new APIException(
                    "Provide workout name",
                    "Workout name cannot be empty/null",
                    HttpStatus.BAD_REQUEST
            );
        }

        String workoutDescription = validator.getDescription();
        if (Utility.isStringEmptyOrNull(workoutDescription)) {
            throw new APIException(
                    "Provide workout description",
                    "Workout description cannot be empty/null",
                    HttpStatus.BAD_REQUEST,
                    40002
            );
        }


        Workout byName = workoutRepository.findByName(workoutName);
        if (byName != null) {
            throw new APIException(
                    "Workout already exists",
                    "Workout with similar name already exists",
                    HttpStatus.CONFLICT
            );
        }
    }
}
