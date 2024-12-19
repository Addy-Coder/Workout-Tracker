package com.workout.tracker.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "wt_workout_log")
public class WorkoutLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "started_at")
    private long startedAt;

    @Column(name = "ended_at")
    private long endedAt;

    @Column(name = "ct")
    private long ct;

    @Column(name = "lu")
    private long lu;
}
