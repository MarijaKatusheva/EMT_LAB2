package com.example.lab2_emt.web.controller;

import com.example.lab2_emt.model.User;
import com.example.lab2_emt.model.exception.InvalidUserCredentialsException;
import com.example.lab2_emt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent","login");
        return "/master-template";
    }
    @PostMapping
    public String login(HttpServletRequest httpServletRequest,Model model){
        String username=httpServletRequest.getParameter("username");
        String password=httpServletRequest.getParameter("password");
        User user=null;
        try{
            user=this.userService.login(username,password);
            httpServletRequest.getSession().setAttribute("user",user);
            return "redirect:/book";

        }catch (InvalidUserCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
