package com.online_nutrition_and_meal_planning_system.controller;

import com.online_nutrition_and_meal_planning_system.service.ProgressAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ProgressAnalyticsController {
    @Autowired
    private ProgressAnalyticsService progressAnalyticsService;
    @GetMapping("/{userId}")
    public Map<String, Integer> getReport(@PathVariable Long userId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return progressAnalyticsService.getNutritionReport(userId, startDate, endDate);
    }
}