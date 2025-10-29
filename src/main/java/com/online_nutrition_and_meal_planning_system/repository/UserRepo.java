package com.online_nutrition_and_meal_planning_system.repository;

import com.online_nutrition_and_meal_planning_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
