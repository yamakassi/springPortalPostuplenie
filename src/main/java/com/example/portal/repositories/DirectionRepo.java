package com.example.portal.repositories;

import com.example.portal.domain.Application;
import com.example.portal.domain.Direction;
import com.example.portal.domain.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DirectionRepo extends JpaRepository<Direction,Long> {
    Optional<Direction> findByAbbr(String abbr);
    void deleteByAbbr(String abbr);


}
