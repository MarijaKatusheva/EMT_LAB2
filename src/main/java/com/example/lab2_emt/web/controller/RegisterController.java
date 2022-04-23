package com.example.lab2_emt.web.controller;

import com.example.lab2_emt.model.enumeration.Role;
import com.example.lab2_emt.model.exception.InvalidArgumentException;
import com.example.lab2_emt.model.exception.PasswordDoNotMatchException;
import com.example.lab2_emt.model.exception.UsernameAlreadyExistsException;
import com.example.lab2_emt.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserServiceImpl userService;

    public RegisterController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    String getRegisterPage(Model model) {
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    String registerUser(@RequestParam String username,
                        @RequestParam String name,
                        @RequestParam String surname,
                        @RequestParam String password,
                        @RequestParam String repeatedPassword,
                        @RequestParam Role role) {
        try {
            if (username == "" || name == "" || surname == "" || password == "" || repeatedPassword == "" || role == null) {
                return "redirect:/register?hasErrorRequired=true&&errorRequired=Required*";
            }
            this.userService.register(username, password, repeatedPassword, name, surname, role);
            return "redirect:/login?hasSuccessful=true&&successful=successful";

        } catch (InvalidArgumentException | UsernameAlreadyExistsException | PasswordDoNotMatchException ex) {
            return "redirect:/register?hasError=true&&error=" + ex.toString();
        }

    }
}
