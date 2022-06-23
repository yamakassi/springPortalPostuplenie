package com.example.portal.controller;

import com.example.portal.domain.Application;
import com.example.portal.domain.Direction;
import com.example.portal.domain.enums.Status;
import com.example.portal.services.ApplicationService;
import com.example.portal.services.DirectionService;
import com.example.portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @PostMapping(value = "/app/{username}", consumes = "application/json")
    @ResponseBody
    public String reqControl(@RequestBody Map<String, String> myMap, @PathVariable String username) {


        Map<String, Direction> directionMap = myMap.entrySet().stream().collect(Collectors.toMap(
                i -> i.getKey(),
                i -> directionService.findDirection(i.getValue())
        ));
        applicationService.saveApplication(directionMap, username);
        return "t";
    }

    @GetMapping("/test")
    public String formCreatingApplication(Principal principal, Model model) {
        model.addAttribute("directs", directionService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("applicationsList", applicationService.list());
        return "hello";
    }

    @PostMapping("/app/{username}/delete")
    public String deleteApp(@PathVariable String username) {
        System.out.println(username);
        applicationService.deleteApp(username);

        return "redirect:/test";
    }

    @PostMapping("/app/{username}/review")
    public String updateReviewApp(@PathVariable String username) {
        System.out.println(username);
        applicationService.updateReviewApp(username,Status.REVIEW);

        return "redirect:/test";
    }

    @GetMapping("/app/list")
    public String confirmApplication(Model model, Principal principal) {

        model.addAttribute("applications", applicationService.findAllByNoConfirmApplications(principal));
        return "confirm-applications";
    }
    @PostMapping("/app/{username}/reject")
    public String updateRejectApp(@PathVariable String username) {

        applicationService.updateReviewApp(username,Status.REJECT);

        return "redirect:/test";
    }
    @PostMapping("/app/{username}/accept")
    public String updateAcceptApp(@PathVariable String username) {
        System.out.println(username);
        applicationService.updateReviewApp(username, Status.ACCEPT);

        return "redirect:/test";
    }


}
