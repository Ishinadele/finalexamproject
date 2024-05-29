package com.example.finalexamproject.CONTROLLER;

import com.example.finalexamproject.DTO.UserLoginDTO;
import com.example.finalexamproject.DTO.UserRegisterDTO;
import com.example.finalexamproject.MODEL.User;
import com.example.finalexamproject.REPOSITORY.UserRepository;
import com.example.finalexamproject.SERVICE.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UsersService usersService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userLoginDTO", new UserLoginDTO());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "redirect:/login?error=user-exists";
        } else {
            user.setAdmin(false); // Assuming new users are not admin by default
            usersService.saveUser(user);
            return "redirect:/login?registrationSuccess=true";
        }
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute UserLoginDTO loginUser, Model model, RedirectAttributes redirectAttributes) {
        User user = usersService.findUserByEmail(loginUser.getEmail());

        if (user != null && loginUser.getPassword().equals(user.getPassword())) {
            if (user.isAdmin()) {
                if (loginUser.isAdmin()) { // Check if the user is an admin and the admin checkbox is checked
                    redirectAttributes.addFlashAttribute("message", "Admin Dashboard");
                    return "redirect:/admin";
                } else {
                    model.addAttribute("error", "Please check the admin checkbox to access the admin dashboard.");
                    model.addAttribute("userLoginDTO", new UserLoginDTO()); // Clear the login form
                    return "login";
                }
            } else {
                if (loginUser.isAdmin()) { // Check if the user is not an admin but the admin checkbox is checked
                    model.addAttribute("error", "You need authorization to access the admin dashboard.");
                    model.addAttribute("userLoginDTO", new UserLoginDTO()); // Clear the login form
                    return "login";
                } else {
                    redirectAttributes.addFlashAttribute("message", "User Dashboard");
                    return "redirect:/shop";
                }
            }
        } else {
            model.addAttribute("error", "Invalid username or password.");
            model.addAttribute("userLoginDTO", loginUser); // Retain email entered
            return "login";
        }
    }
}
