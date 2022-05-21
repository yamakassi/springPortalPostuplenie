package com.example.portal.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String snils;
    //List<Exam> exams = new ArrayList<>();;


    @ManyToOne(cascade = CascadeType.REFRESH)
    private Direction direction;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    private boolean registered = false;
    private LocalDateTime dateOfCreated;
}
