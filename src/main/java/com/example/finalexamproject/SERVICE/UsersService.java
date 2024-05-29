package com.example.finalexamproject.SERVICE;

import com.example.finalexamproject.DTO.UserRegisterDTO;
import com.example.finalexamproject.MODEL.User;
import com.example.finalexamproject.REPOSITORY.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        userRepository.save(user);
    }

    @Transactional
    public void saveUser(User user) {
        try {
            if (user != null && user.getPassword() != null && !user.getPassword().isEmpty()) {
                System.out.println("Saving user: " + user.getEmail());
                userRepository.save(user);
                System.out.println("User saved successfully");
            } else {
                System.out.println("Invalid user data");
            }
        } catch (Exception e) {
            System.out.println("Error saving user:");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean deleteUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public String updateUser(int id, String name, String password, String email) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(name);
            user.setPassword(password);
            user.setEmail(email);
            userRepository.save(user);
            return "User updated";
        }
        return "User not found";
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
