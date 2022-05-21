package com.example.portal.controller;

import com.example.portal.domain.Direction;
import com.example.portal.domain.Institute;
import com.example.portal.services.DirectionService;
import com.example.portal.services.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/institute/{instAbbr}/direction")
public class DirectionController {
    @Autowired
    private DirectionService directionService;
    @Autowired
    private  InstituteService instituteService;



    @GetMapping("/{directionName}")
    public String direction(@PathVariable String instAbbr,@PathVariable String directionName,Model model) {
        model.addAttribute("direction",directionService.getDirectionByInstituteAbbr(instAbbr,directionName));
        return "direction-info";
    }
    @PostMapping("/create")
    public  String createDirection(@PathVariable String instAbbr,Direction direction,Principal principal)  {

       directionService.saveDirection(direction,instAbbr);
        return  "redirect:/institute/{instAbbr}/";
    }

   /* @PostMapping("/delete/{directionName}")
    public String deleteDirection(@PathVariable String abbr){
        directionService.delete(abbr);
        return "redirect:/";
    }*/
}
