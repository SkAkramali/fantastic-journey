package com.fitnessTracking;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Workouts")
public class WorkoutRoutes {
	
	@Autowired
	WorkoutService repo;
	
	@Autowired
	UsersSevice userRepo;
	
	@PostMapping("/add")
	public String add(
			 @RequestParam WorkOutList workoutName,
			 @RequestParam int duration,
			 @RequestParam String date,
			 @RequestParam String notes,
			 HttpSession session
	  
	) {
		
		
		Users curUser = (Users) session.getAttribute("user");
		Workout curWorkout = new  Workout();
		curWorkout.setWorkout(workoutName);
		curWorkout.setDate(LocalDate.parse(date));
		curWorkout.setDuration(duration);
		curWorkout.setNotes(notes);
		curWorkout.setUser(curUser);
		repo.add(curWorkout);
		 return "redirect:/dashboard";
	}
	
	

}
