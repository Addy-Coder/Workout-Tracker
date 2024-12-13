package com.workout.tracker.controller;

import com.workout.tracker.entity.Exercise;
import com.workout.tracker.model.exercise.ExerciseBaseDTO;
import com.workout.tracker.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
@Tag(name = "Exercises", description = "APIs for managing exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @Operation(summary = "Create a new exercise", description = "Adds a new exercise to the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Exercise created successfully",
                    content = @Content(schema = @Schema(implementation = ExerciseBaseDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflict error: Exercise already present",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example =
                                    """
                                            {
                                                "userMessage": "Exercise already present",
                                                "developerMessage": "Exercise already present",
                                                "errorCode": 40901
                                            }
                                            """))),
            @ApiResponse(responseCode = "400", description = "No category found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example =
                                    """
                                            {
                                                "userMessage": "Provide a exercise category",
                                                "developerMessage": "Category cannot be empty/null",
                                                "errorCode": 40001
                                            }
                                            """))),
            @ApiResponse(responseCode = "400", description = "Validation error: Invalid category",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example =
                                    """
                                            {
                                                "userMessage": "Provide a valid category",
                                                "developerMessage": "Provide a valid category",
                                                "errorCode": 40002
                                            }
                                            """))),
            @ApiResponse(responseCode = "400", description = "No muscle group found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example =
                                    """
                                            {
                                                "userMessage": "Provide a exercise muscle group",
                                                "developerMessage":  "Muscle group cannot be empty/null",
                                                "errorCode": 40002
                                            }
                                            """))),
            @ApiResponse(responseCode = "400", description = "Validation error: Invalid muscle group",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example =
                                    """
                                            {
                                                "userMessage": "Provide a valid muscle group",
                                                "developerMessage": "Provide a valid muscle group",
                                                "errorCode": 40003
                                            }
                                            """)))
    })
    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody ExerciseBaseDTO exerciseBaseDTO) {
        Exercise newExercise = exerciseService.createExercise(exerciseBaseDTO);
        return new ResponseEntity<>(newExercise, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all exercises", description = "Fetches all exercises available in the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of exercises retrieved successfully",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No exercises found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> allExercises = exerciseService.getAllExercises();
        return new ResponseEntity<>(allExercises, HttpStatus.OK);
    }

    @Operation(summary = "Get exercise by ID", description = "Fetches an exercise based on its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exercise retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Exercise.class))),
            @ApiResponse(responseCode = "404", description = "Exercise not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable @Schema(description = "ID of the exercise", requiredMode = Schema.RequiredMode.REQUIRED) String exerciseId) {
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @Operation(summary = "Get exercises by category", description = "Fetches exercises belonging to a specific category.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exercises retrieved successfully",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "Invalid category",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No exercises found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Exercise>> getExercisesByCategory(@PathVariable @Schema(description = "Category of the exercises", requiredMode = Schema.RequiredMode.REQUIRED) String category) {
        List<Exercise> exercisesByCategory = exerciseService.getExercisesByCategory(category);
        return new ResponseEntity<>(exercisesByCategory, HttpStatus.OK);
    }

    @Operation(summary = "Get exercises by muscle group", description = "Fetches exercises targeting a specific muscle group.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exercises retrieved successfully",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "Invalid muscle group",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No exercises found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/muscle/{muscle}")
    public ResponseEntity<List<Exercise>> getExercisesByMuscleGroup(@PathVariable(name = "muscle") @Schema(description = "Muscle group of the exercises", requiredMode = Schema.RequiredMode.REQUIRED) String muscleGroup) {
        List<Exercise> exercisesByMuscleGroup = exerciseService.getExercisesByMuscleGroup(muscleGroup);
        return new ResponseEntity<>(exercisesByMuscleGroup, HttpStatus.OK);
    }
}
