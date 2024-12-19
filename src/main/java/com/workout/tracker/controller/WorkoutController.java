package com.workout.tracker.controller;

import com.workout.tracker.entity.Workout;
import com.workout.tracker.entity.WorkoutExercise;
import com.workout.tracker.entity.WorkoutLog;
import com.workout.tracker.entity.WorkoutUser;
import com.workout.tracker.model.workout.WorkoutBaseDTO;
import com.workout.tracker.model.workout.exercise.WorkoutExerciseDTO;
import com.workout.tracker.model.workout.user.WorkoutUserDTO;
import com.workout.tracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody WorkoutBaseDTO workoutBaseDTO) {
        Workout workout = workoutService.createWorkout(workoutBaseDTO);
        return new ResponseEntity<>(workout, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        List<Workout> allWorkouts = workoutService.getAllWorkouts();
        return new ResponseEntity<>(allWorkouts, HttpStatus.OK);
    }

    @GetMapping("/{workoutId}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable String workoutId) {
        Workout workoutById = workoutService.getWorkoutById(workoutId);
        return new ResponseEntity<>(workoutById, HttpStatus.OK);
    }

    @PostMapping("/associate/exercise/{workoutId}")
    public ResponseEntity<List<WorkoutExercise>> associateExercisesToWorkout(@PathVariable String workoutId, @RequestBody List<WorkoutExerciseDTO> workoutExerciseDTOList) {
        List<WorkoutExercise> workoutExerciseList = workoutService.associateExercisesToWorkout(workoutId, workoutExerciseDTOList);
        return new ResponseEntity<>(workoutExerciseList, HttpStatus.OK);
    }

    @PostMapping("/associate/user/{workoutId}")
    public ResponseEntity<List<WorkoutUser>> associateUsersToWorkout(@PathVariable String workoutId, @RequestBody List<WorkoutUserDTO> workoutUserDTOList) {
        List<WorkoutUser> workoutUserList = workoutService.associateUsersToWorkout(workoutId, workoutUserDTOList);
        return new ResponseEntity<>(workoutUserList, HttpStatus.OK);
    }

    @PostMapping("/{workoutId}/start")
    public ResponseEntity<WorkoutLog> startUserWorkout(@PathVariable String workoutId, @RequestParam String userId) {
        WorkoutLog workoutLog = workoutService.startUserWorkout(workoutId, userId);
        return new ResponseEntity<>(workoutLog, HttpStatus.OK);
    }

    @PostMapping("/{workoutId}/end")
    public ResponseEntity<WorkoutLog> endUserWorkout(@PathVariable String workoutId, @RequestParam String userId) {
        WorkoutLog workoutLog = workoutService.endUserWorkout(workoutId, userId);
        return new ResponseEntity<>(workoutLog, HttpStatus.OK);
    }

    // TODO : de-associate workout with exercise
    // TODO : de-associate workout with user
}
