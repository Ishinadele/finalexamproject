package com.example.finalexamproject.CONTROLLER;

import com.example.finalexamproject.DTO.GlobalDTO;
//import com.example.finalexamproject.SERVICE.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckoutController {
    @Autowired
    //private EmailService emailService;
    @PostMapping("/payNow")
   /* public String payNow() {
        String userEmail = "ishinadele12@gmail.com";
        String subject = "Thank You To Purchase!";
        String text = "YOU'RE ALWAYS WELCOME TO SERVE YOU IS OUR PRIORITY";
        emailService.sendEmail(userEmail, subject, text);
        GlobalDTO.cart.clear();
        return "redirect:/confirmation";
    }*/

    // Endpoint for displaying the confirmation page
    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }
}
