package com.example.portal.repositories;

import com.example.portal.domain.Application;
import com.example.portal.domain.Direction;
import com.example.portal.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepo extends JpaRepository<Application, Long> {
    Optional<Direction> findBySnils(String abbr);


    List<Application> findByStatusAppEquals(Status statusApp);


    void deleteBySnils(String abbr);


}
