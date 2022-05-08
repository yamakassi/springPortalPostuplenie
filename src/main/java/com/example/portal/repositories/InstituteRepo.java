package com.example.portal.repositories;

import com.example.portal.domain.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstituteRepo extends JpaRepository<Institute, Long> {

    Optional<Institute> findByAbbr(String abbr);
    void deleteByAbbr(String abbr);
}

