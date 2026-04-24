package com.fitnessTracking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
