package com.example.finalexamproject.MODEL;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Collection;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    @NotEmpty
    @Email(message = "{errors.invalid}")
    private String email;

    @NotEmpty
    private String password;

    @Column(nullable = false)
    private boolean admin;

}


