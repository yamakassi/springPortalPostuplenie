package com.example.portal.controller;

import com.example.portal.domain.Application;
import com.example.portal.domain.Direction;
import com.example.portal.domain.Institute;
import com.example.portal.domain.User;
import com.example.portal.services.DirectionService;
import com.example.portal.services.InstituteService;
import com.example.portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/institute/{instAbbr}/direction")
public class DirectionController {
    @Autowired
    private DirectionService directionService;
    @Autowired
    private UserService userService;



    @GetMapping("/{directionName}")
    public String direction(@PathVariable String instAbbr,
                            @PathVariable String directionName,
                            Principal principal,
                            Model model) {


        model.addAttribute("user", principal.getName());
        model.addAttribute("direction",directionService.getDirectionByInstituteAbbr(instAbbr,directionName));
        return "direction-info";
    }
    @PostMapping("/create")
    public  String createDirection(@PathVariable String instAbbr,Direction direction)  {

       directionService.saveDirection(direction,instAbbr);
        return  "redirect:/institute/{instAbbr}/";
    }
    @GetMapping("/{directionName}/app")
    public String findApplication(@PathVariable String instAbbr,
                                  @PathVariable String directionName,
                                  Model model){

        Set<Application> applicationList =  directionService.getDirectionByInstituteAbbr(instAbbr,directionName).getApplications();
        model.addAttribute("applications",applicationList);
        return "list-applications";
    }
   /* @PostMapping("/delete/{directionName}")
    public String deleteDirection(@PathVariable String abbr){
        directionService.delete(abbr);
        return "redirect:/";
    }*/
}
