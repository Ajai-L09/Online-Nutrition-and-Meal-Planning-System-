package com.online_nutrition_and_meal_planning_system.model;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "foodId")
    private FoodItem foodItem;
}
