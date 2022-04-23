package com.example.lab2_emt.service.impl;

import com.example.lab2_emt.model.User;
import com.example.lab2_emt.model.enumeration.Role;
import com.example.lab2_emt.model.exception.InvalidArgumentException;
import com.example.lab2_emt.model.exception.InvalidUserCredentialsException;
import com.example.lab2_emt.model.exception.PasswordDoNotMatchException;
import com.example.lab2_emt.model.exception.UsernameAlreadyExistsException;
import com.example.lab2_emt.repository.jpa.UserRepository;
import com.example.lab2_emt.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password,String repeatPassword, String name, String surname, Role role) {
         if(username=="" || password==""|| role==null ){
             throw new InvalidArgumentException();
         }
         if(!password.equals(repeatPassword)){
            throw new PasswordDoNotMatchException();
         }
         if(this.userRepository.findByUsername(username).isPresent()){
             throw new UsernameAlreadyExistsException(username);
         }
         User user=new User(username,passwordEncoder.encode(password),name,surname,role);
        return this.userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if(username=="" || password==""){
            throw new InvalidArgumentException();
        }
            User user=this.userRepository.findByUsernameAndPassword(username,password)
                    .orElseThrow(() -> new InvalidUserCredentialsException());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow( ()-> new UsernameNotFoundException(username));
    }
}
