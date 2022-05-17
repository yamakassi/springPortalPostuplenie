package com.example.portal.controller;

import com.example.portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private  UserService userService;



    @GetMapping()
    public String admin(Model model){
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }
    @PostMapping("/user/disable/{id}")
    public String userDisable(@PathVariable("id") Long id){

        userService.disableUser(id);
        return "redirect:/admin";
    }
}
