package com.online_nutrition_and_meal_planning_system.repository;

import com.online_nutrition_and_meal_planning_system.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepo extends JpaRepository<FoodItem, Long> {

}
