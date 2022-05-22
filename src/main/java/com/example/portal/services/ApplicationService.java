package com.example.portal.services;

import com.example.portal.domain.Application;
import com.example.portal.domain.Direction;
import com.example.portal.domain.User;
import com.example.portal.repositories.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepo applicationRepo;
    public List<Application> list() {
        return applicationRepo.findAll();
    }
    public void saveApplication(Application application){


        for(Direction d:application.getDirections()){
            d.addApplication(application);
        }
        applicationRepo.save(application);
    }


}
