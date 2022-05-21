package com.example.portal.repositories;

import com.example.portal.domain.Exam;
import com.example.portal.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepo extends JpaRepository<Exam, Long> {
}
