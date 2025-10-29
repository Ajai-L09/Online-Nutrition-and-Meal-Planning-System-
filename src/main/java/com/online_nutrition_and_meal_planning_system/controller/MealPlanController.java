package com.online_nutrition_and_meal_planning_system.controller;

import com.online_nutrition_and_meal_planning_system.model.Log;
import com.online_nutrition_and_meal_planning_system.service.MealPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat; // <-- Import this
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/plan")
public class MealPlanController {

    @Autowired
    private MealPlanningService mealPlanningService;
    @PostMapping
    public Log createPlanEntry(@RequestParam Long userId, @RequestParam Long foodId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date, @RequestParam String mealType) {
        return mealPlanningService.createPlan(userId, foodId, date, mealType);
    }
    @GetMapping("/{userId}")
    public List<Log> getPlan(@PathVariable Long userId) {
        return mealPlanningService.getPlanForUser(userId);
    }
}