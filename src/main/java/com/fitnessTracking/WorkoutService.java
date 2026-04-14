package com.fitnessTracking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
	
	@Autowired
	WorkOutRepo workouts;
	
	public void add(Workout curWorkout) {
		workouts.save(curWorkout);
	}
	
	public List<Workout> getWorkouts(Users user) {
		return workouts.findByUser(user);
	}
	

}
