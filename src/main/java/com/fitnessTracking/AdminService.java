package com.fitnessTracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepo repo;

    public Admin findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public long count() {
        return repo.count();
    }

    public Admin save(Admin admin) {
        return repo.save(admin);
    }
}
