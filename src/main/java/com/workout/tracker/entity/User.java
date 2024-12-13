package com.workout.tracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "wt_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "ct")
    private long ct;

    @Column(name = "lu")
    private long lu;
}
