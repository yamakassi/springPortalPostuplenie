package com.example.portal.controller;

import com.example.portal.domain.User;
import com.example.portal.domain.enums.Role;
import com.example.portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

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

/* --- add super admin func modification user.roles
    @GetMapping("/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model, Principal principal) {
       // model.addAttribute("user", user);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }*/
}
