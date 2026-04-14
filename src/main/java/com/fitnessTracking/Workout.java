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
public class Workout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Enumerated(EnumType.STRING)
	WorkOutList workout;
	
	int duration;
	
	LocalDate date;
	
	String notes;

	@ManyToOne
	@JoinColumn(name = "Users_email")
	Users user;
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Workout() {
		super();
	}

	public Workout(int id, WorkOutList workout, int duration, LocalDate date, String notes) {
		super();
		this.id = id;
		this.workout = workout;
		this.duration = duration;
		this.date = date;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public WorkOutList getWorkout() {
		return workout;
	}

	public void setWorkout(WorkOutList workout) {
		this.workout = workout;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	


}
