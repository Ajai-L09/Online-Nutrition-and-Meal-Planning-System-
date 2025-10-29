package com.online_nutrition_and_meal_planning_system.service;

import com.online_nutrition_and_meal_planning_system.model.FoodItem;
import com.online_nutrition_and_meal_planning_system.model.Log;
import com.online_nutrition_and_meal_planning_system.model.User;
import com.online_nutrition_and_meal_planning_system.repository.FoodItemRepo;
import com.online_nutrition_and_meal_planning_system.repository.LogRepo;
import com.online_nutrition_and_meal_planning_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodLogService {
    @Autowired
    private LogRepo logRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FoodItemRepo foodItemRepo;

    public Log logMeal(Long userId, Long foodId, String mealType, Date date){
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        FoodItem foodItem = foodItemRepo.findById(foodId)
                .orElseThrow(() -> new RuntimeException("FoodItem not found"));
        Log NewLog = new Log();
        NewLog.setUser(user);
        NewLog.setFoodItem(foodItem);
        NewLog.setDate(date);
        NewLog.setMealType(mealType);
        NewLog.setStatus("Eaten");
        return logRepo.save(NewLog);
    }

    public Log updateLogStatusToEaten(Long logId) {
        Log log = logRepo.findById(logId)
                .orElseThrow(() -> new RuntimeException("Log not found"));
        log.setStatus("Eaten");
        return logRepo.save(log);
    }

    public List<Log> getLogsForUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return logRepo.findByUser(user).stream().filter(log->"Eaten".equals(log.getStatus())).collect(Collectors.toList());
    }
}
