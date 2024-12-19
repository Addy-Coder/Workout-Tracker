package com.workout.tracker.service;

import com.workout.tracker.entity.*;
import com.workout.tracker.model.workout.WorkoutBaseDTO;
import com.workout.tracker.model.workout.exercise.WorkoutExerciseDTO;
import com.workout.tracker.model.workout.user.WorkoutUserDTO;

import java.util.List;
import java.util.Optional;

public interface WorkoutService {
    Workout createWorkout(WorkoutBaseDTO workoutBaseDTO);
    List<Workout> getAllWorkouts();
    Workout getWorkoutById(String workoutId);
    Workout updateWorkoutById(String workoutId);
    Workout deleteWorkoutById(String workoutId);
    List<WorkoutExercise> associateExercisesToWorkout(String workoutId, List<WorkoutExerciseDTO> exerciseIds);
    List<WorkoutUser> associateUsersToWorkout(String workoutId, List<WorkoutUserDTO> workoutUserDTOList);
    WorkoutLog startUserWorkout(String workoutId, String userId);
    WorkoutLog endUserWorkout(String workoutId, String userId);
}
