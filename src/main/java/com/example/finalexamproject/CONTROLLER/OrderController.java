package com.example.finalexamproject.CONTROLLER;

import com.example.finalexamproject.DTO.OrderDTO;
import com.example.finalexamproject.MODEL.Order;
import com.example.finalexamproject.MODEL.User;
import com.example.finalexamproject.SERVICE.OrderService;
import com.example.finalexamproject.SERVICE.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UsersService usersService;

    @GetMapping("/new")
    public String showOrderForm(Model model, Principal principal) {
        if (principal == null) {
            // Redirect to login page if user is not authenticated
            return "redirect:/login";
        }

        User user = usersService.findUserByEmail(principal.getName());

        model.addAttribute("error", "You do not have permission to create orders.");
        return "error-page";

    }



    @PostMapping
    public String createOrder(@Valid OrderDTO orderDTO, BindingResult bindingResult, Model model, Principal principal) {
        User user = usersService.findUserByEmail(principal.getName());

        model.addAttribute("error", "You do not have permission to create orders.");
        return "error-page";

    }

    @GetMapping("/history")
    public String viewOrderHistory(Principal principal, Model model) {
        User user = usersService.findUserByEmail(principal.getName());

        model.addAttribute("error", "You do not have permission to view order history.");
        return "error-page";

    }

    @GetMapping("/all")
    public String viewAllOrders(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("error", "You are not logged in.");
            return "error-page";
        }

        User user = usersService.findUserByEmail(principal.getName());

        model.addAttribute("error", "You do not have permission to view all orders.");
        return "error-page";

    }

}
