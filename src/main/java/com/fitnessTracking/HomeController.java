package com.fitnessTracking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String homePage(HttpSession session) {
		if(session.getAttribute("user") == null) {
			
			return "index";
		}
		return "redirect:/dashboard";
	}

}
