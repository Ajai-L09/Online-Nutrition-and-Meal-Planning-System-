package com.online_nutrition_and_meal_planning_system.service;

import com.online_nutrition_and_meal_planning_system.model.FoodItem;
import com.online_nutrition_and_meal_planning_system.model.Log;
import com.online_nutrition_and_meal_planning_system.model.User;
import com.online_nutrition_and_meal_planning_system.repository.FoodItemRepo;
import com.online_nutrition_and_meal_planning_system.repository.LogRepo;
import com.online_nutrition_and_meal_planning_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LogRepo logRepo;
    @Autowired
    private FoodItemRepo foodItemRepo;

    public List<FoodItem> getRecommendations(Long userId){
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        Date startDate = getStartOfToday();
        Date endDate = getEndOfToday();

        List<Log> todayLogs = logRepo.findByUserAndStatusAndDateBetween(user, "Eaten", startDate, endDate);
        int caloriesEaten = todayLogs.stream().mapToInt(log -> log.getFoodItem().getCalories()).sum();
        int remainingCalories = user.getDailyCalorieGoal() - caloriesEaten;

        List<FoodItem> allFoods = foodItemRepo.findAll();

        return allFoods.stream()
                .filter(food -> food.getCalories() <= remainingCalories)
                .collect(Collectors.toList());
    }
    private Date getStartOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    private Date getEndOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }
}
