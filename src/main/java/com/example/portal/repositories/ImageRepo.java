package com.example.portal.repositories;

import com.example.portal.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}