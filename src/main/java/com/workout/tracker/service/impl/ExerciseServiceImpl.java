package com.workout.tracker.service.impl;

import com.workout.tracker.entity.Exercise;
import com.workout.tracker.exception.APIException;
import com.workout.tracker.exception.ExceptionUtility;
import com.workout.tracker.model.exercise.ExerciseBaseDTO;
import com.workout.tracker.repository.ExerciseRepository;
import com.workout.tracker.service.ExerciseService;
import com.workout.tracker.service.validation.ValidateInput;
import com.workout.tracker.service.validation.impl.exercise.CreateExerciseValidator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    @ValidateInput(validator = CreateExerciseValidator.class)
    public Exercise createExercise(ExerciseBaseDTO exerciseBaseDTO) {
        long currentTimeMillis = System.currentTimeMillis();
        Exercise exercise = Exercise.builder()
                .name(exerciseBaseDTO.getName())
                .description(exerciseBaseDTO.getDescription())
                .category(Exercise.Category.valueOf(exerciseBaseDTO.getCategory()))
                .muscleGroup(Exercise.MuscleGroup.valueOf(exerciseBaseDTO.getMuscleGroup()))
                .ct(currentTimeMillis)
                .lu(currentTimeMillis)
                .build();

        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        if (exercises.isEmpty()) {
            throw ExceptionUtility.noDataAvailable();
        }
        return exercises;
    }

    @Override
    public Exercise getExerciseById(String exerciseId) {
        return exerciseRepository.findById(exerciseId).orElseThrow(() -> new APIException(
                "Exercise not found",
                "No exercise found with the given id",
                HttpStatus.NOT_FOUND
        ));
    }

    @Override
    public List<Exercise> getExercisesByCategory(String category) {
        Exercise.Category categoryEnum;

        try {
            categoryEnum = Exercise.Category.valueOf(category);
        } catch (IllegalArgumentException e) {
            throw new APIException(
                    "Invalid category",
                    "Invalid category",
                    HttpStatus.BAD_REQUEST
            );
        }

        return exerciseRepository.findByCategory(categoryEnum).orElseThrow(ExceptionUtility::noDataAvailable);
    }

    @Override
    public List<Exercise> getExercisesByMuscleGroup(String muscleGroup) {
        Exercise.MuscleGroup muscleGroupEnum;

        try {
            muscleGroupEnum = Exercise.MuscleGroup.valueOf(muscleGroup);
        } catch (IllegalArgumentException e) {
            throw new APIException(
                    "Invalid muscle group",
                    "Invalid muscle group",
                    HttpStatus.BAD_REQUEST
            );
        }

        return exerciseRepository.findByMuscleGroup(muscleGroupEnum).orElseThrow(ExceptionUtility::noDataAvailable);
    }

    @Override
    public Exercise updateExercise(String exerciseId) {
        return null;
    }

    @Override
    public Exercise deleteExercise(String exerciseId) {
        return null;
    }
}
