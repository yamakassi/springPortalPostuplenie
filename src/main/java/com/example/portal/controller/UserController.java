package com.example.portal.controller;

import com.example.portal.domain.User;
import com.example.portal.services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login() {

        return "login";

    }
 /*   @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile";
    }*/
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if(!userService.createUser(user)){
           // model.addAttribute("errorMessage", "Пользователь с email:" + user.getEmail() + ", уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(user);
        model.addAttribute("user", user);

       // model.addAttribute("user-", user);

        return "user-info";

    }

}
