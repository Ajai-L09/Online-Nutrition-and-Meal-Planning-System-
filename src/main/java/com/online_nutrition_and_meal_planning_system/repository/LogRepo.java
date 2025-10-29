package com.online_nutrition_and_meal_planning_system.repository;

import com.online_nutrition_and_meal_planning_system.model.Log;
import com.online_nutrition_and_meal_planning_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepo extends JpaRepository<Log, Long> {

    List<Log> findByUser(User user);
    List<Log> findByUserAndStatusAndDate(User user, String status, String startDate, String endDate);
}
