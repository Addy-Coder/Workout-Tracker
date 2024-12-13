package com.workout.tracker.repository;

import com.workout.tracker.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, String> {
    Workout findByName(String name);
}
