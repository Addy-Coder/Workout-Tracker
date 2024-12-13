package com.workout.tracker.service.validation.impl.exercise;

import com.workout.tracker.entity.Exercise;
import com.workout.tracker.exception.APIException;
import com.workout.tracker.model.exercise.ExerciseBaseDTO;
import com.workout.tracker.utility.Utility;
import org.springframework.http.HttpStatus;

public class CreateExerciseValidator extends ExerciseServiceValidator {
    @Override
    public void validate(ExerciseBaseDTO validator) {
        Exercise existingExercise = exerciseRepository.findByName(validator.getName());
        if (existingExercise != null) {
            throw new APIException(
                    "Exercise already present",
                    "Exercise already present",
                    HttpStatus.CONFLICT
            );
        }
        String category = validator.getCategory();

        if (Utility.isStringEmptyOrNull(category)) {
            throw new APIException(
                    "Provide a exercise category",
                    "Category cannot be empty/null",
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            Exercise.Category.valueOf(category);
        } catch (IllegalArgumentException e) {
            throw new APIException(
                    "Provide a valid category",
                    "Provide a valid category",
                    HttpStatus.BAD_REQUEST,
                    40002
            );
        }

        String muscleGroup = validator.getMuscleGroup();

        if (Utility.isStringEmptyOrNull(muscleGroup)) {
            throw new APIException(
                    "Provide a exercise muscle group",
                    "Muscle group cannot be empty/null",
                    HttpStatus.BAD_REQUEST,
                    40002
            );
        }

        try {
            Exercise.MuscleGroup.valueOf(muscleGroup);
        } catch (IllegalArgumentException e) {
            throw new APIException(
                    "Provide a valid muscle group",
                    "Provide a valid muscle group",
                    HttpStatus.BAD_REQUEST,
                    40003
            );
        }
    }
}
