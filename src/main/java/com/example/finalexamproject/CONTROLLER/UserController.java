package com.example.finalexamproject.CONTROLLER;

import com.example.finalexamproject.REPOSITORY.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    @Autowired
    private UserRepository userRepository;

}
