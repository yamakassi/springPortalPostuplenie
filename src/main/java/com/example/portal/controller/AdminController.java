package com.example.portal.controller;

import com.example.portal.domain.enums.Role;
import com.example.portal.domain.users.PersonalInfo;
import com.example.portal.domain.users.User;
import com.example.portal.services.ApplicationService;
import com.example.portal.services.InstituteService;
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
@PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private InstituteService instituteService;


    @GetMapping()
    public String admin(Model model) {
        model.addAttribute("allAdmins", userService.allAdmins()); // userService.allAdmins()
        model.addAttribute("institutes", instituteService.getAll());
        return "admin";
    }

    @PostMapping("/user/disable/{id}")
    public String userDisable(@PathVariable("id") Long id) {

        userService.disableUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/registration")
    public String createUser(User user, PersonalInfo persInfo, Model model) {

        user.setPersonalInfo(persInfo);
        if (!userService.createUser(user, Role.ROLE_ADMIN)) {
            // model.addAttribute("errorMessage", "Пользователь с email:" + user.getEmail() + ", уже существует");
            return "admin";
        }
        model.addAttribute("institutes", instituteService.getAll());
        return "redirect:/admin";
    }


    /* --- add super admin func modification user.roles*/
    @GetMapping("/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {

        model.addAttribute("user", user);

        model.addAttribute("institutes", instituteService.getAll());
        return "user-change";
    }

    @PostMapping("/user/edit")
    public String userEdit(@RequestParam("userId") User user, String department) {
        user.setDepartament(department);

        userService.updateUser(user);
        return "redirect:/admin";
    }
}
