package com.fitnessTracking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	

	public List<Integer> getWeeklyCalories(String email) {

        LocalDate today = LocalDate.now();
        LocalDate start = today.minusDays(6);

        List<Workout> list =
                workouts.findByUser_EmailAndDateBetween(email, start, today);

        Map<LocalDate, Integer> map = new HashMap<>();

        for (Workout w : list) {

            // 🔥 CALCULATE CALORIES FROM DURATION
            int calories = calculateCalories(w);

            map.put(
                w.getDate(),
                map.getOrDefault(w.getDate(), 0) + calories
            );
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 6; i >= 0; i--) {
            LocalDate d = today.minusDays(i);
            result.add(map.getOrDefault(d, 0));
        }

        return result;
    }

     private int calculateCalories(Workout w) {

        switch (w.getWorkout()) {

            case RUNNING:
                return w.getDuration() * 10;

            case GYM:
                return w.getDuration() * 7;

            case  CYCLING:
                return w.getDuration() * 6;

            default:
                return w.getDuration() * 5;
        }
    }
}
