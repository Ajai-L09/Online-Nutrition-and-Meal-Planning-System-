package com.online_nutrition_and_meal_planning_system.service;

import com.online_nutrition_and_meal_planning_system.model.User;
import com.online_nutrition_and_meal_planning_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(String username, String password, String role, int dailyCalorieGoal) {
        if (userRepo.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(role);
        user.setDailyCalorieGoal(dailyCalorieGoal);
        return userRepo.save(user);
    }

    public String login(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found"));

        if (passwordEncoder.matches(password, user.getPasswordHash())) {
            return "Login Successful! UserID: " + user.getUserId();
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public User updateProfile(Long userId, int dailyCalorieGoal) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setDailyCalorieGoal(dailyCalorieGoal);
        return userRepo.save(user);
    }
}