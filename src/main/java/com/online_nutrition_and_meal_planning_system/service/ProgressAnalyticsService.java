package com.online_nutrition_and_meal_planning_system.service;

import com.online_nutrition_and_meal_planning_system.model.FoodItem;
import com.online_nutrition_and_meal_planning_system.model.Log;
import com.online_nutrition_and_meal_planning_system.model.User;
import com.online_nutrition_and_meal_planning_system.repository.LogRepo;
import com.online_nutrition_and_meal_planning_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgressAnalyticsService {
    @Autowired
    private LogRepo logRepo;
    @Autowired
    private UserRepo userRepo;

    public Map<String, Integer> getNutritionReport(Long userId, Date startDate, Date endDate){
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        List<Log> logs = logRepo.findByUserAndStatusAndDateBetween(user, "Eaten", startDate,endDate);
        int totCalories = 0;
        int totProtein = 0;
        int totCarbs = 0;
        int totFat = 0;
        for (Log log : logs){
            FoodItem item = log.getFoodItem();
            totCalories += item.getCalories();
            totProtein += item.getProtein();
            totCarbs += item.getCarbs();
            totFat += item.getFat();
        }
        Map<String, Integer> report = new HashMap<>();
        report.put("totCalories", totCalories);
        report.put("totProtein", totProtein);
        report.put("totCarbs", totCarbs);
        report.put("totFat", totFat);

        return report;
    }

}
