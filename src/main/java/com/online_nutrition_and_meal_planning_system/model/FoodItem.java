package com.online_nutrition_and_meal_planning_system.model;


import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "food_items")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodId")
    private Long foodItemId;

    private String name;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;

    @OneToMany(mappedBy = "foodItem")
    private List<Log> logs;
}
