package com.workout.tracker.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "wt_workout_user")
public class WorkoutUser {
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

    @Column(name = "assigned_at")
    private long assignedAt;

    @Column(name = "ct")
    private long ct;

    @Column(name = "lu")
    private long lu;
}
