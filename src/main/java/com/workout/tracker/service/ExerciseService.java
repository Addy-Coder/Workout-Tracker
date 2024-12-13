package com.workout.tracker.service;

import com.workout.tracker.entity.Exercise;
import com.workout.tracker.model.exercise.ExerciseBaseDTO;

import java.util.List;

public interface ExerciseService {
    Exercise createExercise(ExerciseBaseDTO exerciseBaseDTO);
    List<Exercise> getAllExercises();
    Exercise getExerciseById(String exerciseId);
    List<Exercise> getExercisesByCategory(String category);
    List<Exercise> getExercisesByMuscleGroup(String muscleGroup);
    Exercise updateExercise(String exerciseId);
    Exercise deleteExercise(String exerciseId);
}
