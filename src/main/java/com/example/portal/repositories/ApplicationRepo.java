package com.example.portal.repositories;

import com.example.portal.domain.Application;
import com.example.portal.domain.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepo extends JpaRepository<Application,Long> {
    Optional<Direction> findBySnils(String abbr);
    void deleteBySnils(String abbr);



    List<Application> findByRegisteredIsFalse();
}
