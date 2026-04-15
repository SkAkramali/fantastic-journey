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
	
	public static double  calculateCurrentValue(Goal goal, List<Workout> workouts) {

	    double value = 0;

	    for (Workout w : workouts) {

	        switch (goal.getGoalType()) {

	            case CALORIE_BURN:
	                value += w.getDuration() * 10;
	                break;

	            case WORKOUTS:
	                value += 1;
	                break;

	            case WEIGHT_LOSS:
 	                break;
	        }
	    }
	    
	    

	    return value;
	}
	public void updateGoalProgress(Goal goal, List<Workout> workouts) {

        double total = 0;

        for (Workout w : workouts) {

            // ✅ FIXED DATE CHECK (INCLUSIVE)
            if ((w.getDate().isEqual(goal.getStartDate()) || w.getDate().isAfter(goal.getStartDate())) &&
                (w.getDate().isEqual(goal.getEndDate()) || w.getDate().isBefore(goal.getEndDate()))) {

                switch (goal.getGoalType()) {

                    case CALORIE_BURN:
                        total += w.getDuration() * 10;
                        break;

                    case WORKOUTS:
                        total += 1;
                        break;

                    default:
                        break;
                }
            }
        }

        // ✅ UPDATE CURRENT VALUE
        goal.setCurrentValue(total);

        // ✅ FIXED STATUS LOGIC
        if (total >= goal.getTargetValue()) {
            goal.setStatus("COMPLETED");
        } else {
            goal.setStatus("ACTIVE");
        }
    }


}
