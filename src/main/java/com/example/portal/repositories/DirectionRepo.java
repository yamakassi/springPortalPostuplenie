package com.example.portal.repositories;

import com.example.portal.domain.Direction;
import com.example.portal.domain.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectionRepo extends JpaRepository<Direction,Long> {
    Optional<Direction> findByAbbr(String abbr);
    void deleteByAbbr(String abbr);
}
