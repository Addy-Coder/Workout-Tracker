package com.workout.tracker.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "wt_workout_exercise")
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @Column(name = "repetitions")
    private int repetitions;

    @Column(name = "sets")
    private int sets;

    @Column(name = "ct")
    private long ct;

    @Column(name = "lu")
    private long lu;
}
