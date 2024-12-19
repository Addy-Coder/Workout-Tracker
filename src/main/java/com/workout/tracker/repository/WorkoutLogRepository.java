package com.workout.tracker.repository;

import com.workout.tracker.entity.User;
import com.workout.tracker.entity.Workout;
import com.workout.tracker.entity.WorkoutLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutLogRepository extends JpaRepository<WorkoutLog, String> {
    Optional<WorkoutLog> findByWorkoutAndUserAndEndedAtIsNull(Workout workout, User user);
}
