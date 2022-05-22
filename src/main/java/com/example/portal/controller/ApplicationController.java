package com.example.portal.controller;

import com.example.portal.domain.Application;
import com.example.portal.services.ApplicationService;
import com.example.portal.services.DirectionService;
import com.example.portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ApplicationController {
    @Autowired
    private DirectionService directionService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UserService userService;
  /*  @Autowired
    private ApplicationService applicationService;

    @PostMapping("app/create")
    public  String createDirection(ExamsDTO examsDTO, User user)  {
        System.out.println("@@@@@@@@@@@@@@@@@@@");


    }*/
    @PostMapping("/app/add")
    public String addApplication(Application application, Principal principal){
        applicationService.saveApplication(application);
        userService.addApplication(userService.getUserByPrincipal(principal),application);
        return "redirect:/";
    }
    @GetMapping("/test")
    public String formCreatingApplication(Model model){
        model.addAttribute("directs",directionService.list());
        model.addAttribute("application", new Application());
        model.addAttribute("applicationsList", applicationService.list());
        return "hello";
    }


































}
