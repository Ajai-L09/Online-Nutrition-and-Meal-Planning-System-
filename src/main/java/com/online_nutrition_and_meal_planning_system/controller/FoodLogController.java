package com.online_nutrition_and_meal_planning_system.controller;

import com.online_nutrition_and_meal_planning_system.model.Log;
import com.online_nutrition_and_meal_planning_system.service.FoodLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/log")
public class FoodLogController {
    @Autowired
    private FoodLogService foodLogService;
    @PostMapping
    public Log logMeal(@RequestParam Long userId, @RequestParam Long foodId, @RequestParam String mealType, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        return foodLogService.logMeal(userId, foodId, mealType, date);
    }

    @PutMapping("/eat/{logId}")
    public Log updateStatusToEaten(@PathVariable Long logId) {
        return foodLogService.updateLogStatusToEaten(logId);
    }
    @GetMapping("/{userId}")
    public List<Log> getEatenLogs(@PathVariable Long userId) {
        return foodLogService.getLogsForUser(userId);
    }
}