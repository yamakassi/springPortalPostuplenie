package com.example.portal.controller;

import com.example.portal.ExamsDTO;
import com.example.portal.domain.Exam;
import com.example.portal.domain.users.PersonalInfo;
import com.example.portal.domain.users.User;
import com.example.portal.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login() {

        return "login";

    }
    @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("exams", new ExamsDTO());
        return "profile";
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, PersonalInfo persInfo, Model model) {

    user.setPersonalInfo(persInfo);
        if(!userService.createUser(user)){
           // model.addAttribute("errorMessage", "Пользователь с email:" + user.getEmail() + ", уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
    // -- для просмотра админом
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {

        model.addAttribute("user", user);
    model.addAttribute("exams", new ExamsDTO());


        return "user-info";

    }
    @PostMapping("/user/{user}/app/create")
    public  String createDirection(ExamsDTO examsDTO, @PathVariable("user") User user)  {


        List<Exam> exams= examsDTO.transferExam(user);

        userService.saveExams(exams);


        //directionService.saveDirection(direction,instAbbr);
        return  "profile";
    }

}
