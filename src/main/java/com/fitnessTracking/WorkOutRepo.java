package com.fitnessTracking;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOutRepo extends JpaRepository<Workout, Integer> {

	public List<Workout> findByUser(Users user);
	List<Workout> findByUser_EmailAndDateBetween(
            String email,
            LocalDate start,
            LocalDate end
    );

}
