package com.fitnessTracking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class UserDetailsRoutes {
	
	@Autowired
	WorkoutService repo;

	
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
		List workouts = repo.getWorkouts(curUser);
 	    model.addAttribute("workouts", workouts); 
	    return "sampleDashboard";

	}
	

}
