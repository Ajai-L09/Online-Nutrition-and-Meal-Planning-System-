package com.online_nutrition_and_meal_planning_system.controller;

import com.online_nutrition_and_meal_planning_system.model.FoodItem;
import com.online_nutrition_and_meal_planning_system.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;
    @GetMapping("/{userId}")
    public List<FoodItem> getRecommendations(@PathVariable Long userId) {
        return recommendationService.getRecommendations(userId);
    }
}