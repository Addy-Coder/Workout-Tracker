package com.workout.tracker.model.workout.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkoutExerciseDTO {
    private String exerciseId;
    private int sets;
    private int repetitions;
}
