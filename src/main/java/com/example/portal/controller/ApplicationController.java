package com.example.portal.controller;

import com.example.portal.ExamsDTO;
import com.example.portal.domain.Direction;
import com.example.portal.domain.Exam;
import com.example.portal.domain.User;
import com.example.portal.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ApplicationController {
  /*  @Autowired
    private ApplicationService applicationService;

    @PostMapping("app/create")
    public  String createDirection(ExamsDTO examsDTO, User user)  {
        System.out.println("@@@@@@@@@@@@@@@@@@@");

       List<Exam>  exams= examsDTO.transferExam();
        System.out.println(exams);
        System.out.println(user);
        //directionService.saveDirection(direction,instAbbr);
        return  "hello";
    }*/
}
