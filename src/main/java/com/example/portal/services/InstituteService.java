package com.example.portal.services;

import com.example.portal.domain.Institute;

import com.example.portal.repositories.InstituteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstituteService {
    @Autowired
    private InstituteRepo instituteRepo;



    public List<Institute> list() {
        return instituteRepo.findAll();
    }

    public void saveInstitute(Institute institute) {
        instituteRepo.save(institute);
    }
    @Transactional
    public void delete(String instituteAbbr) {
      instituteRepo.deleteByAbbr(instituteAbbr);
    }

    public Institute getInstituteByTitle(String abbr) {
        return instituteRepo.findByAbbr(abbr).orElse(null);
    }

}
