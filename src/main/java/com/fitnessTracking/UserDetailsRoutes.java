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
@RequestMapping("/dashboard")
public class UserDetailsRoutes {
	
	@Autowired
	WorkoutService repo;

	@Autowired
	GoalService goalService;
	
	@Autowired
	WorkOutRepo workoutRepo;
	@Autowired
	GoalRepo goalRepo;
	
	@GetMapping("/profile")
	public String profile(Model model, HttpSession session) {

	    Users user = (Users) session.getAttribute("user");

	    System.out.println(user);
	    model.addAttribute("user", user); 

	    return "profile";
	}
	
	@GetMapping("")
	public String getWorkouts(Model model, HttpSession session){

	    Users curUser = (Users) session.getAttribute("user");

	    List<Workout> workouts = workoutRepo.findByUser(curUser);
	    List<Goal> goals = goalRepo.findByUser(curUser);

 	    for (Goal g : goals) {
	        goalService.updateGoalProgress(g, workouts);
	    }

	    model.addAttribute("workouts", workouts);
	    model.addAttribute("goals", goals);

	    return "sampleDashboard";
	}	
	@GetMapping("/setGoal")
	public String getGoalFrom() {
		return "goalSetForm";
	}
	
	@PostMapping("/setGoal")
	public String setGoal( 
	   @RequestParam GoalType goalType,
	   @RequestParam double targetValue,
	   @RequestParam LocalDate targetDate,
	   @RequestParam String notes,
	   HttpSession session
	) {

	    Goal curGoal = new Goal();
	    curGoal.setGoalType(goalType);
 	    curGoal.setTargetValue(targetValue);
	    curGoal.setCurrentValue(0);
	    curGoal.setStartDate(LocalDate.now()); 
	    curGoal.setEndDate(targetDate);
	    curGoal.setStatus("ACTIVE");
	    curGoal.setNotes(notes);
	    curGoal.setUser((Users) session.getAttribute("user"));
	    goalService.setGoal(curGoal);

	    return "redirect:/dashboard";
	}	
//	@GetMapping("/goals")
//	public String getGoals(Model model, HttpSession session) {
//		Users user = (Users) session.getAttribute("user");
//		List<Goal> goals = goalService.getAllGoals(user);
//		model.addAttribute("goals", goals);
//		return "Goals";
//	}
	@GetMapping("/goals")
	public String getGoals(HttpSession session, Model model) {

	    Users user = (Users) session.getAttribute("user");

	    List<Goal> goals = goalRepo.findByUser(user);
	    List<Workout> workouts = workoutRepo.findByUser(user);

	    for (Goal g : goals) {
	        goalService.updateGoalProgress(g, workouts);
	    }

	    model.addAttribute("goals", goals);

	    return "goals";
	}
}
