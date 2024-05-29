

package com.example.finalexamproject.DTO;


import com.example.finalexamproject.MODEL.Category;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private Category category;
    private double price;
    private double weight;
    private String description;
    private String imageName;
}
