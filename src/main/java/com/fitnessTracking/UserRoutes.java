package com.fitnessTracking;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/auth")
public class UserRoutes {
	@Autowired
	UsersSevice r;
	
	@GetMapping("/signup")
	public String register() {
 
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(
			 @RequestParam String name,
		        @RequestParam String email,
		        @RequestParam String password,
		        @RequestParam int age,
		        @RequestParam Gender gender,
		        @RequestParam double height,
		        @RequestParam double weight
	) {
		
		Users user = new Users();
		user.setEmail(email);
		user.setAge(age);
		user.setGender(gender);
		user.setHeight(height);
		user.setWeight(weight);
		user.setPassword(password);
		user.setName(name);
		
		
		r.insert(user);
		
		return "redirect:/auth/signin";
	}
	
	@GetMapping("signin")
	public String signin() {
		
		return "signin";
		
	}
	
	@PostMapping("signin")
	public String signin(  @RequestParam String email,
		          @RequestParam String password,
		          HttpSession session
			) {
		Users match = r.findByEmail(email);
		if(match == null) {
			return "redirect:/auth/signup";
		}
		if(match.getPassword().equals(password)) {
			 session.setAttribute("user", match);
			 return "redirect:/dashboard";
		}
		return "redirect:/auth/signup";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
