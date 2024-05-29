package com.example.finalexamproject.DTO;



import com.example.finalexamproject.MODEL.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalDTO {
    public static List<Product> cart;
    static {
        cart = new ArrayList<Product>();
    }
}
