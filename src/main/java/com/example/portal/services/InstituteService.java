package com.example.portal.services;

import com.example.portal.domain.Image;
import com.example.portal.domain.Institute;

import com.example.portal.domain.User;
import com.example.portal.repositories.InstituteRepo;
import com.example.portal.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstituteService {
    @Autowired
    private InstituteRepo instituteRepo;
    @Autowired
    private UserRepo userRepo;


    public List<Institute> list() {
        return instituteRepo.findAll();
    }

    public void saveInstitute(Institute institute, MultipartFile file) throws IOException {
        Image image;
        if(!file.isEmpty()){
            image = ImageService.toImageEntity(file);
            institute.setImage(image);
            image.setInstitute(institute);
        }
        
        instituteRepo.save(institute);
    }
    @Transactional
    public void delete(String instituteAbbr) {
      instituteRepo.deleteByAbbr(instituteAbbr);
    }

    public Institute getInstituteByTitle(String abbr) {
        return instituteRepo.findByAbbr(abbr).orElse(null);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {

            return new User();
        }

        return userRepo.findByEmail(principal.getName()).orElse(new User());

    }
}
