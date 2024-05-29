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

        if (user == null || !user.getRoles().contains("ROLE_USER")) {
            model.addAttribute("error", "You do not have permission to create orders.");
            return "error-page";
        }

        model.addAttribute("orderDTO", new OrderDTO());
        return "order-form";
    }



    @PostMapping
    public String createOrder(@Valid OrderDTO orderDTO, BindingResult bindingResult, Model model, Principal principal) {
        User user = usersService.findUserByEmail(principal.getName());

        if (user == null || !user.getRoles().contains("ROLE_USER")) {
            model.addAttribute("error", "You do not have permission to create orders.");
            return "error-page";
        }

        if (bindingResult.hasErrors()) {
            return "order-form";
        }

        orderDTO.setUserId(user.getId().longValue());
        Order order = orderService.createOrder(orderDTO);
        model.addAttribute("order", order);
        return "order-success";
    }

    @GetMapping("/history")
    public String viewOrderHistory(Principal principal, Model model) {
        User user = usersService.findUserByEmail(principal.getName());

        if (user == null || (!user.getRoles().contains("ROLE_USER") && !user.getRoles().contains("ROLE_ADMIN"))) {
            model.addAttribute("error", "You do not have permission to view order history.");
            return "error-page";
        }

        List<Order> orders;
        if (user.getRoles().contains("ROLE_ADMIN")) {
            orders = orderService.findAllOrders();
        } else {
            orders = orderService.findOrdersByUserEmail(user.getEmail());
        }

        model.addAttribute("orders", orders);
        return user.getRoles().contains("ROLE_ADMIN") ? "order-history-admin" : "order-history";
    }

    @GetMapping("/all")
    public String viewAllOrders(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("error", "You are not logged in.");
            return "error-page";
        }

        User user = usersService.findUserByEmail(principal.getName());

        if (user == null || !user.getRoles().contains("ROLE_ADMIN")) {
            model.addAttribute("error", "You do not have permission to view all orders.");
            return "error-page";
        }

        List<Order> orders = orderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "order-history-admin";
    }

}
