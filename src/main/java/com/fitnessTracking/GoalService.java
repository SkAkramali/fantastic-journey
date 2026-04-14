package com.fitnessTracking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {
	
	@Autowired
	GoalRepo repo;
	
	public void setGoal(Goal goal) {
		repo.save(goal);
	}
	
	public List<Goal>getAllGoals(Users user) {
	    return repo.findByUser(user);
	}

}
