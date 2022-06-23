package com.example.portal.services;

import com.example.portal.domain.Application;
import com.example.portal.domain.Direction;
import com.example.portal.domain.enums.Status;
import com.example.portal.domain.users.User;
import com.example.portal.repositories.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private UserService userService;

    public List<Application> list() {
        return applicationRepo.findAll();
    }

    public void saveApplication(Map<String, Direction> directions, String username) {
        User user = userService.findUserByUsername(username);
        String mainInst = mainInstitute(directions.values().stream()
                .map(i -> i.getInstitute().getAbbr())
                .collect(Collectors.toList()));

        Application app = new Application(directions);
        System.out.println(mainInst);
        app.setMainInst(mainInst);
        app.setUser(user);
        for (Direction d : directions.values()) {
            d.addApplication(app);
        }
        userService.save(user, app);

        // applicationRepo.save(app);
    }


    public List<Application> findAllByNoConfirmApplications(Principal principal) {
        User admin = userService.getUserByPrincipal(principal);
        List<Application> apps = applicationRepo.findByStatusAppEquals(Status.REVIEW);

        return applicationRepo.findByStatusAppEquals(Status.REVIEW)
                .stream()
                .filter(a -> a.getMainInst().equals(admin.getDepartament()))
                .collect(Collectors.toList());

    }

    public void deleteApp(String username) {
        User user = userService.findUserByUsername(username);
        Application app = user.getApplication();
        for (Direction d : app.getDirections().values()) {
            d.removeApplication(app);
        }
        app.setUser(null);
        user.setApplication(null);
        applicationRepo.delete(app);
    }

    public void updateReviewApp(String username, Status review) {
        User user = userService.findUserByUsername(username);
        Application app = user.getApplication();
        app.setStatusApp(review);

        System.out.println(user.getApplication());
        applicationRepo.save(app);
    }


    private String mainInstitute(List<String> listInst) {
        Map<String, Long> instByCount = listInst.stream()
                .collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));

        String prevalentInst = instByCount.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();

        System.out.println(prevalentInst);

        return prevalentInst;
    }
}
