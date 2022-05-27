package com.example.portal.controller;

import com.example.portal.domain.users.*;
import com.example.portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
@RequestMapping("/lk")
public class LkController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String lk() {
        return "lk/lk";
    }

    @GetMapping("/PersonalInfo")
    public String personalInfo(Principal principal, Model model) {
        model.addAttribute("persInfo", userService.getUserByPrincipal(principal).getPersonalInfo());
        return "lk/personal-info";
    }
    @PostMapping("/PersonalInfo")
    public String editPersonalInfo(Principal principal, PersonalInfo persInfo){
        userService.editPersonalInfo(principal,persInfo);
        return "lk/lk";
    }

    @GetMapping("/Passport")
    public String passport( Principal principal, Model model) {

        model.addAttribute("passport", userService.getUserByPrincipal(principal).getPassport());
        return "lk/passport";
    }
    @PostMapping("/Passport")
    public String editPassport(Principal principal, Passport pass) {
         userService.editPassport(principal,pass);
        return "lk/passport";
    }


    @GetMapping("/Contacts")
    public String contacts(Principal principal, Model model) {
        model.addAttribute("contacts", userService.getUserByPrincipal(principal).getContacts());
        return "lk/contacts";
    }
    @PostMapping("/Contacts")
    public String editContacts(Principal principal, Contacts contacts) {
        userService.editContacts(principal,contacts);
        return "lk/contacts";
    }

    @GetMapping("/CurrentEducation")
    public String currentEducation(Principal principal, Model model) {
        model.addAttribute("currentEducation", userService.getUserByPrincipal(principal).getCurrentEducation());
        return "lk/current-education";
    }
    @PostMapping("/CurrentEducation")
    public String currentEducation(Principal principal, CurrentEducation education) {
        userService.editEducation(principal,education);
        return "lk/contacts";
    }

    @GetMapping("/AddInfo")
    public String addInfo(Principal principal, Model model) {
        model.addAttribute("addInfo", userService.getUserByPrincipal(principal).getAdditInfo());

        return "lk/add-info";
    }
    @PostMapping("/AddInfo")
    public String addInfo(Principal principal, AddInfo additInfo) {
        userService.editAdditInfo(principal,additInfo);
        return "lk/contacts";
    }
    @GetMapping("/Files")
    public String files() {
        return "lk/files";
    }

    @GetMapping("/UserSettings")
    public String userSettings() {
        return "lk/user-settings";
    }


}
