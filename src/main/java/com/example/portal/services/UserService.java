package com.example.portal.services;

import com.example.portal.domain.Application;
import com.example.portal.domain.CertificateJSON;
import com.example.portal.domain.Exam;
import com.example.portal.domain.Image;
import com.example.portal.domain.users.*;
import com.example.portal.domain.enums.Role;
import com.example.portal.repositories.ExamRepo;
import com.example.portal.repositories.ImageRepo;
import com.example.portal.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    public PasswordEncoder passwordEncoder;

    public boolean createUser(User user, Role role) {
        if (!userRepo.findByEmail(user.getEmail()).isEmpty()) {

            return false;
        }
        user.setActive((true));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(role);
        userRepo.save(user);
        return true;
    }


    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public void disableUser(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setActive(false);
        }
        userRepo.save(user);

    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName()).get();
    }

    public void saveExams(List<Exam> exams) {
        for (Exam exam : exams) {

            examRepo.save(exam);
        }
    }

    public void addApplication(User user, Application application) {
        user.setApplication(application);
        userRepo.save(user);
    }

    public void editPersonalInfo(Principal principal, PersonalInfo persInfo) {

        User user = getUserByPrincipal(principal);
        user.setPersonalInfo(persInfo);
        userRepo.save(user);
    }

    public void editPassport(Principal principal, Passport pass) {
        User user = getUserByPrincipal(principal);
        user.setPassport(pass);
        userRepo.save(user);
    }

    public void editContacts(Principal principal, Contacts contacts) {
        User user = getUserByPrincipal(principal);
        user.setContacts(contacts);
        userRepo.save(user);
    }

    public void editEducation(Principal principal, CurrentEducation education) {
        User user = getUserByPrincipal(principal);
        user.setCurrentEducation(education);
        userRepo.save(user);


    }

    public void editAdditInfo(Principal principal, AddInfo additInfo) {
        User user = getUserByPrincipal(principal);
        user.setAdditInfo(additInfo);
        userRepo.save(user);

    }


    public void addFiles(Principal principal, String fileType, String comment, MultipartFile file) {
        User user = getUserByPrincipal(principal);

        Image image;
        if (!file.isEmpty()) {

            try {
                image = ImageService.toImageEntity(file);

                image.setName(fileType);
                image.setOriginalFileName(user.getEmail() + "_" + fileType);
                image.setComment(comment);
                user.addFiles(image);
                image.setUser(user);
                imageRepo.save(image);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        userRepo.save(user);
    }


    public void changeUserRoles(User user, Map<String, String> form) {

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);

    }

    public void addSpravka(Principal principal, CertificateJSON cert) {
        User user = getUserByPrincipal(principal);
        user.setSpravka(true);
        userRepo.save(user);
    }

    public List<User> allAdmins() {
        return  userRepo.findAll()
                .stream()
                .filter(user->user.getRoles()
                        .contains(Role.ROLE_ADMIN))
                .collect(Collectors.toList());
        //return userRepo.findAllUsersIsRoleAdmin();
    }


    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Transactional
    public void save(User user, Application app) {
        user.setApplication(app);
        userRepo.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepo.findByEmail(username).get();
    }


}
