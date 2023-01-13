package com.example.demo.service;

import com.example.demo.model.UserRole;
import com.example.demo.model.CustomUser;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public CustomUser findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    @Transactional
    public boolean addUser(String login, String userName,
                            String userPhone, String email,
                            String password, UserRole userRole){
        if (userRepository.findByLogin(login)!=null)
            return false;
        CustomUser user = new CustomUser(login, userName, userPhone, email, password, userRole);
        userRepository.save(user);
        return true;
    }
}