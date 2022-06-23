package com.example.portal.repositories;

import com.example.portal.domain.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DirectionRepo extends JpaRepository<Direction, Long> {
    Optional<Direction> findByAbbr(String abbr);

    void deleteByAbbr(String abbr);


    List<Direction> findAll();


}
