package com.example.demo.service;

import com.example.demo.model.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserAuthorizationService implements UserDetailsService {

    private final UserService userService;

    public UserAuthorizationService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        CustomUser customUser = userService.findByLogin(login);
        if (customUser == null) {
            throw new UsernameNotFoundException(login + " not found");
        }

        List<GrantedAuthority> roles =Arrays.asList(
                        new SimpleGrantedAuthority(customUser.getUserRole().toString()));

        return new User(customUser.getLogin(), customUser.getPassword(), roles);
    }
}