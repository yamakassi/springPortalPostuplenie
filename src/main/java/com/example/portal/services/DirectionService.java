package com.example.portal.services;

import com.example.portal.domain.Application;
import com.example.portal.domain.Direction;
import com.example.portal.domain.Image;
import com.example.portal.repositories.DirectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class DirectionService {
    @Autowired
    private DirectionRepo directionRepo;
    @Autowired
    private InstituteService instituteService;

    public List<Direction> list() {
        return directionRepo.findAll();
    }


    @Transactional
    public void delete(String directionAbbr) {
        directionRepo.deleteByAbbr(directionAbbr);
    }

    public Direction getDirectionByTitle(String abbr) {
        return directionRepo.findByAbbr(abbr).orElse(null);
    }

    public Direction getDirectionByInstituteAbbr(String instAbbr, String directionName) {

        return instituteService.getInstituteByAbbr(instAbbr).getDirections().stream().filter(x -> x.getAbbr().equals(directionName)).findAny().get();

    }

    public void saveDirection(Direction direction, String instAbbr) {
        direction.setInstitute(instituteService.getInstituteByAbbr(instAbbr));

        directionRepo.save(direction);
    }


    public Direction findDirection(String abbr) {
        return directionRepo.findByAbbr(abbr).orElseThrow(() -> new RuntimeException("Не найдено направление"));
    }
}
