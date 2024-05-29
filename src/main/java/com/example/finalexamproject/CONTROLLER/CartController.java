package com.example.finalexamproject.CONTROLLER;


import com.example.finalexamproject.DTO.GlobalDTO;
import com.example.finalexamproject.MODEL.Product;
import com.example.finalexamproject.SERVICE.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        GlobalDTO.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String cartGet(Model model) {
        model.addAttribute("cartCount", GlobalDTO.cart.size());
        model.addAttribute("total", GlobalDTO.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalDTO.cart);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index) {
        GlobalDTO.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total", GlobalDTO.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";}
}

