package com.workout.tracker.repository;

import com.workout.tracker.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, String> {
    Exercise findByName(String name);

    Optional<List<Exercise>> findByCategory(Exercise.Category category);

    Optional<List<Exercise>> findByMuscleGroup(Exercise.MuscleGroup muscleGroup);
}
