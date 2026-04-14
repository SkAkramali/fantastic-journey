package com.fitnessTracking;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Goal {

	
	public Goal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GoalType getGoalType() {
		return goalType;
	}

	public void setGoalType(GoalType goalType) {
		this.goalType = goalType;
	}

	public double getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(double targetValue) {
		this.targetValue = targetValue;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Goal(int id, GoalType goalType, double targetValue, double currentValue, LocalDate startDate,
			LocalDate endDate, String status, Users user, String notes) {
		super();
		this.id = id;
		this.goalType = goalType;
		this.targetValue = targetValue;
		this.currentValue = currentValue;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.user = user;
		this.notes = notes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	
	@Enumerated(EnumType.STRING)
	GoalType goalType;
	
	 private double targetValue;
	 private double currentValue;

	 private LocalDate startDate;
	 private LocalDate endDate;

	 private String status;
	 
	 String notes;
	 

	 public String getNotes() {
		return notes;
	}

	 public void setNotes(String notes) {
		 this.notes = notes;
	 }

	 @ManyToOne
	 @JoinColumn(name = "user_email")
	 private Users user;
}
