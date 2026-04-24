package com.fitnessTracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdminService adminService;

    @Override
    public void run(String... args) {
        if (adminService.count() == 0) {
            adminService.save(new Admin("admin", "admin123"));
            System.out.println("========================================");
            System.out.println(" Default admin created");
            System.out.println(" Username : admin");
            System.out.println(" Password : admin123");
            System.out.println(" URL      : /admin/login");
            System.out.println("========================================");
        }
    }
}
