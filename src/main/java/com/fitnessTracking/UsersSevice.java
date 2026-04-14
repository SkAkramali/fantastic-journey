package com.fitnessTracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class UsersSevice {
	@Autowired
	UsersRepo repo;
	
	public void insert(Users curUser) {
		repo.save(curUser);
	}
	
	public Users findByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	

}
