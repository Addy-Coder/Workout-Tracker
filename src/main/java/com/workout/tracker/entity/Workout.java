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
@Table(name = "wt_workout")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "ct")
    private long ct;

    @Column(name = "lu")
    private long lu;
}
