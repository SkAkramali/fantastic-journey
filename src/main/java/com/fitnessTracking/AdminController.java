package com.fitnessTracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private UsersRepo usersRepo;
    @Autowired private WorkOutRepo workoutRepo;
    @Autowired private GoalRepo goalRepo;

    /* ── Auth ── */

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("admin") != null) return "redirect:/admin/dashboard";
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        Admin admin = adminService.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            session.setAttribute("admin", admin);
            return "redirect:/admin/dashboard";
        }
        return "redirect:/admin/login?error";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/admin/login";
    }

    /* ── Dashboard ── */

    @GetMapping({"", "/dashboard"})
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("admin") == null) return "redirect:/admin/login";

        List<Users>   users    = usersRepo.findAll();
        List<Workout> workouts = workoutRepo.findAll();
        List<Goal>    goals    = goalRepo.findAll();

        model.addAttribute("totalUsers",    users.size());
        model.addAttribute("totalWorkouts", workouts.size());
        model.addAttribute("totalGoals",    goals.size());

        int uc = Math.min(5, users.size());
        model.addAttribute("recentUsers", users.subList(users.size() - uc, users.size()));

        int wc = Math.min(8, workouts.size());
        model.addAttribute("recentWorkouts", workouts.subList(workouts.size() - wc, workouts.size()));

        return "admin/dashboard";
    }

    /* ── Users ── */

    @GetMapping("/users")
    public String users(Model model, HttpSession session) {
        if (session.getAttribute("admin") == null) return "redirect:/admin/login";
        model.addAttribute("users", usersRepo.findAll());
        return "admin/users";
    }

    @GetMapping("/users/detail")
    public String userDetail(@RequestParam String email,
                             Model model,
                             HttpSession session) {
        if (session.getAttribute("admin") == null) return "redirect:/admin/login";
        Users user = usersRepo.findById(email).orElse(null);
        if (user == null) return "redirect:/admin/users";
        model.addAttribute("user",     user);
        model.addAttribute("workouts", workoutRepo.findByUser(user));
        model.addAttribute("goals",    goalRepo.findByUser(user));
        return "admin/user-detail";
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam String email, HttpSession session) {
        if (session.getAttribute("admin") == null) return "redirect:/admin/login";
        usersRepo.deleteById(email);
        return "redirect:/admin/users";
    }
}
