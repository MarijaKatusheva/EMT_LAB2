package com.example.lab2_emt.service;

import com.example.lab2_emt.model.User;
import com.example.lab2_emt.model.enumeration.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String password,String repeatPassword, String name, String surname, Role role);
    User login(String username,String password);
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
