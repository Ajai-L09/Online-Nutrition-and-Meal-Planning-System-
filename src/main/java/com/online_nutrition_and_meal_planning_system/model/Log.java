package com.online_nutrition_and_meal_planning_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;
    private Date date;
    private String mealType;
    private String status;

    @ManyToOne
    private User user;
    @ManyToOne
    private FoodItem foodItem;
}
