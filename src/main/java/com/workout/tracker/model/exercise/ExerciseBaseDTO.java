package com.workout.tracker.model.exercise;

import com.workout.tracker.entity.Exercise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExerciseBaseDTO {
    private String name;
    private String description;
    private String category;
    private String muscleGroup;
}
