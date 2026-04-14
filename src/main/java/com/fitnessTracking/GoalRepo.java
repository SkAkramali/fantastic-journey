package com.fitnessTracking;

 import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepo extends JpaRepository<Goal, Integer>{
   public List<Goal> findByUser(Users user);
}
