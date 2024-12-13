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
@Table(name = "wt_exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "muscle_group")
    private MuscleGroup muscleGroup;

    @Column(name = "ct")
    private long ct;

    @Column(name = "lu")
    private long lu;

    public enum Category{
        cardio, strength, flexibility
    }
    public enum MuscleGroup{
        chest,back,shoulders,biceps,triceps,forearms,quadriceps,hamstrings,glutes,calves,abs,neck,hips,serratus,adductors,abductors,rhomboids,trapezius,quads,legs
    }
}
