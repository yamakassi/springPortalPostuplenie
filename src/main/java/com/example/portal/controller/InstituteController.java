package com.example.portal.controller;

import com.example.portal.domain.Institute;

import com.example.portal.services.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RequestMapping("/portal")
public class InstituteController {
    @Autowired
    private  InstituteService instituteService;



    @GetMapping
    public String portal(Model model) {
        model.addAttribute("institute", instituteService.list());
        return "portal";
    }
    @GetMapping("/institute/{abbr}")
    public String instituteInfo(@PathVariable String abbr,Model model){

        model.addAttribute("institute",instituteService.getInstituteByTitle(abbr));
        return "institute-info";
    }

    @PostMapping("/institute/create")
    public  String createInstitute(Institute institute){

        instituteService.saveInstitute(institute);
        return  "redirect:/";
    }

    @PostMapping("/institute/delete/{abbr}")
    public String deleteInstitute(@PathVariable String abbr){
        instituteService.delete(abbr);
        return "redirect:/";
    }


}