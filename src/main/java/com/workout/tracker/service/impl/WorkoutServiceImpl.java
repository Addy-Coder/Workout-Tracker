package com.workout.tracker.service.impl;

import com.workout.tracker.entity.Exercise;
import com.workout.tracker.entity.Workout;
import com.workout.tracker.entity.WorkoutExercise;
import com.workout.tracker.exception.APIException;
import com.workout.tracker.exception.ExceptionUtility;
import com.workout.tracker.model.workout.WorkoutBaseDTO;
import com.workout.tracker.model.workout.exercise.WorkoutExerciseDTO;
import com.workout.tracker.repository.ExerciseRepository;
import com.workout.tracker.repository.WorkoutExerciseRepository;
import com.workout.tracker.repository.WorkoutRepository;
import com.workout.tracker.service.WorkoutService;
import com.workout.tracker.service.validation.ValidateInput;
import com.workout.tracker.service.validation.impl.workout.CreateWorkoutValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public WorkoutServiceImpl(WorkoutRepository workoutRepository, WorkoutExerciseRepository workoutExerciseRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.workoutExerciseRepository = workoutExerciseRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    @ValidateInput(validator = CreateWorkoutValidator.class)
    public Workout createWorkout(WorkoutBaseDTO workoutBaseDTO) {
        long currentTimeMillis = System.currentTimeMillis();
        Workout workout = Workout.builder()
                .name(workoutBaseDTO.getName())
                .description(workoutBaseDTO.getDescription())
                .ct(currentTimeMillis)
                .lu(currentTimeMillis)
                .build();
        return workoutRepository.save(workout);
    }

    @Override
    public List<Workout> getAllWorkouts() {
        List<Workout> workouts = workoutRepository.findAll();
        if (workouts.isEmpty()) {
            throw ExceptionUtility.noDataAvailable();
        }
        return workouts;
    }

    @Override
    public Workout getWorkoutById(String workoutId) {
        return workoutRepository.findById(workoutId).orElseThrow(ExceptionUtility::invalidWorkoutId);
    }


    @Override
    public Workout updateWorkoutById(String workoutId) {
        return null;
    }

    @Override
    public Workout deleteWorkoutById(String workoutId) {
        return null;
    }

    @Override
    public List<WorkoutExercise> associateExercisesToWorkout(String workoutId, List<WorkoutExerciseDTO> workoutExerciseDTOList) {

        Workout workout = workoutRepository.findById(workoutId).orElseThrow(ExceptionUtility::invalidWorkoutId);

        List<String> exerciseIds = workoutExerciseDTOList.stream().map(WorkoutExerciseDTO::getExerciseId).toList();

        List<Exercise> exercises = exerciseRepository.findAllById(exerciseIds);

        Map<String, Exercise> validExerciseIdMap = exercises.stream()
                .collect(Collectors.toMap(Exercise::getId, Function.identity()));


        List<String> invalidExerciseIds = exerciseIds.stream().filter(id -> !validExerciseIdMap.containsKey(id)).toList();

        if (!invalidExerciseIds.isEmpty()) {
            throw new APIException(
                    "Provide valid exercise ids",
                    "Invalid exercise ids provided : " + invalidExerciseIds,
                    HttpStatus.BAD_REQUEST
            );
        }

        List<WorkoutExercise> workoutExerciseList = new LinkedList<>();

        for (WorkoutExerciseDTO workoutExerciseDTO : workoutExerciseDTOList) {
            long currentTimeMillis = System.currentTimeMillis();
            workoutExerciseList.add(
                    WorkoutExercise.builder()
                            .workout(workout)
                            .exercise(validExerciseIdMap.get(workoutExerciseDTO.getExerciseId()))
                            .sets(workoutExerciseDTO.getSets())
                            .repetitions(workoutExerciseDTO.getRepetitions())
                            .ct(currentTimeMillis)
                            .lu(currentTimeMillis)
                            .build()
            );
        }


        return workoutExerciseRepository.saveAll(workoutExerciseList);
    }
}
