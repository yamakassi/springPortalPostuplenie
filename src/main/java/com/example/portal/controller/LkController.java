package com.example.portal.controller;

import com.example.portal.domain.CertificateJSON;
import com.example.portal.domain.Image;
import com.example.portal.domain.users.*;
import com.example.portal.services.ImageService;
import com.example.portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/lk")
public class LkController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;

    @GetMapping()
    public String lk(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "lk/lk";
    }

    @GetMapping("/PersonalInfo")
    public String personalInfo(Principal principal, Model model) {
        System.out.println(userService.getUserByPrincipal(principal).getPersonalInfo());
        model.addAttribute("persInfo", userService.getUserByPrincipal(principal).getPersonalInfo());
        return "lk/personal-info";
    }

    @PostMapping("/PersonalInfo")
    public String editPersonalInfo(Principal principal, PersonalInfo persInfo) {
        userService.editPersonalInfo(principal, persInfo);
        return "lk/spravka";
    }

    @GetMapping("/Passport")
    public String passport(Principal principal, Model model) {

        model.addAttribute("passport", userService.getUserByPrincipal(principal).getPassport());
        return "lk/passport";
    }

    @PostMapping("/Passport")
    public String editPassport(Principal principal, Passport pass) {
        System.out.println(pass);
        userService.editPassport(principal, pass);
        return "lk/contacts";
    }


    @GetMapping("/Contacts")
    public String contacts(Principal principal, Model model) {
        model.addAttribute("contacts", userService.getUserByPrincipal(principal).getContacts());
        return "lk/contacts";
    }

    @PostMapping("/Contacts")
    public String editContacts(Principal principal, Contacts contacts) {
        userService.editContacts(principal, contacts);
        return "lk/currentEducation";
    }

    @GetMapping("/CurrentEducation")
    public String currentEducation(Principal principal, Model model) {
        model.addAttribute("currentEducation", userService.getUserByPrincipal(principal).getCurrentEducation());
        return "lk/current-education";
    }

    @PostMapping("/CurrentEducation")
    public String currentEducation(Principal principal, CurrentEducation education) {
        userService.editEducation(principal, education);
        return "lk/files";
    }

    @GetMapping("/AddInfo")
    public String addInfo(Principal principal, Model model) {

        model.addAttribute("addInfo", userService.getUserByPrincipal(principal).getAdditInfo());

        return "lk/add-info";
    }

    @PostMapping("/AddInfo")
    public String addInfo(Principal principal, AddInfo additInfo) {
        userService.editAdditInfo(principal, additInfo);
        return "lk/contacts";
    }

    @GetMapping("/spravka")
    public String spravka(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("spravka", user.isSpravka());
        model.addAttribute("pass", user.getPersonalInfo());
        return "lk/spravka";
    }

    @PostMapping("/spravka")
    public String getCert(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        String snils = user.getPersonalInfo().getSnils();
        String type = "086y";
        RestTemplate template = new RestTemplate();

        String url = "http://192.168.43.177:8080/query?snils=" + snils + "&type=" + type;

        CertificateJSON cert = template.getForObject(url, CertificateJSON.class);

        if (cert != null && cert.isConclusion()) {
            userService.addSpravka(principal, cert);
            return "lk/lk";
        } else {
            return "error/error";
        }

    }


    @GetMapping("/Files")
    public String files(Principal principal, Model model) {
        List<Image> images = userService.getUserByPrincipal(principal).getImages();

        model.addAttribute("files", images);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        // model.addAttribute("imagesName", imageService.findImagesNamesByUsername( userService.getUserByPrincipal(principal).getId()));
        return "lk/files";
    }

    @PostMapping("/AddFile")
    public String addFileUser(Principal principal, String fileType, String comment, @RequestParam("file") MultipartFile file) {


        userService.addFiles(principal, fileType, comment, file);
        return "redirect:/lk/Files";
    }

    @GetMapping("/UserSettings")
    public String userSettings() {
        return "lk/user-settings";
    }


}
