package com.online_nutrition_and_meal_planning_system.repository;

import com.online_nutrition_and_meal_planning_system.model.Log;
import com.online_nutrition_and_meal_planning_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface LogRepo extends JpaRepository<Log, Long> {

    List<Log> findByUser(User user);
    List<Log> findByUserAndStatusAndDateBetween(User user, String status, Date startDate, Date endDate);
}
