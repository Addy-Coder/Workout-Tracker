package com.workout.tracker.model.workout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkoutBaseDTO {
    private String name;
    private String description;
}
