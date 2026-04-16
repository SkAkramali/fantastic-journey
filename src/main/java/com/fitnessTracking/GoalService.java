package com.fitnessTracking;

import java.time.LocalDate;
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

    public List<Goal> getAllGoals(Users user) {
        return repo.findByUser(user);
    }

    public static double calculateCurrentValue(Goal goal, List<Workout> workouts) {

        double value = 0;

        if (goal == null || workouts == null) return 0;

        for (Workout w : workouts) {

            if (w == null) continue;

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

        if (goal == null || workouts == null) return;

        double total = 0;

         LocalDate startDate = goal.getStartDate();
        LocalDate endDate = goal.getEndDate();

        for (Workout w : workouts) {

            if (w == null || w.getDate() == null) continue;

            LocalDate workoutDate = w.getDate();

             boolean afterStart = (startDate == null) ||
                    workoutDate.isEqual(startDate) ||
                    workoutDate.isAfter(startDate);

            boolean beforeEnd = (endDate == null) ||
                    workoutDate.isEqual(endDate) ||
                    workoutDate.isBefore(endDate);

            if (afterStart && beforeEnd) {

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

         goal.setCurrentValue(total);

         Double target = goal.getTargetValue();  

        if (target != null && total >= target) {
            goal.setStatus("COMPLETED");
        } else {
            goal.setStatus("ACTIVE");
        }

         repo.save(goal);
    }
}