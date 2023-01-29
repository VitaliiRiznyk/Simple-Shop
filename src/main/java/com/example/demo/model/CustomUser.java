package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;
    private String userName;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String password;
    private String userPhone;

    private String email;

    public CustomUser(String login, String userName, String userPhone, String email, String password, UserRole userRole) {
        this.userName = userName;
        this.login = login;
        this.userPhone = userPhone;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }
}