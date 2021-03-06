package com.example.portal.controller;

import com.example.portal.domain.Institute;

import com.example.portal.domain.users.User;
import com.example.portal.services.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
//@RequestMapping("/portal")
public class InstituteController {
    @Autowired
    private InstituteService instituteService;


    @GetMapping()
    public String portal(Principal principal, Model model) {

        model.addAttribute("institute", instituteService.list());
        model.addAttribute("user", instituteService.getUserByPrincipal(principal));
        return "portal";
    }

    @GetMapping("/institute/{abbr}")
    public String instituteInfo(@PathVariable String abbr, Model model) {

        model.addAttribute("institute", instituteService.getInstituteByAbbr(abbr));

        return "institute-info";
    }

    @PostMapping("/institute/create")
    public String createInstitute(@RequestParam("file") MultipartFile file, Institute institute) throws IOException {

        instituteService.saveInstitute(institute, file);
        return "redirect:/";
    }

    @PostMapping("/institute/delete/{abbr}")
    public String deleteInstitute(@PathVariable String abbr) {
        instituteService.delete(abbr);
        return "redirect:/";
    }


}
