package com.online_nutrition_and_meal_planning_system.model;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    private String username;
    private String passwordHash;
    private String role;
    private int dailyCalorieGoal;

    @OneToMany(mappedBy = "user")
    private List<Log> logs;
}