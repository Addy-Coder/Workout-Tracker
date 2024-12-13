package com.workout.tracker.service;

import com.workout.tracker.entity.Exercise;
import com.workout.tracker.entity.Workout;
import com.workout.tracker.entity.WorkoutExercise;
import com.workout.tracker.model.workout.WorkoutBaseDTO;
import com.workout.tracker.model.workout.exercise.WorkoutExerciseDTO;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {
    Workout createWorkout(WorkoutBaseDTO workoutBaseDTO);
    List<Workout> getAllWorkouts();
    Workout getWorkoutById(String workoutId);
    Workout updateWorkoutById(String workoutId);
    Workout deleteWorkoutById(String workoutId);
    List<WorkoutExercise> associateExercisesToWorkout(String workoutId, List<WorkoutExerciseDTO> exerciseIds);
}
