package com.example.portal.domain;

import com.example.portal.domain.enums.Role;
import com.example.portal.domain.enums.Status;
import com.example.portal.domain.users.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String snils;
    @ManyToMany
    @JoinTable(name = "direction_application",
            joinColumns = {@JoinColumn(name = "id_application")},
            inverseJoinColumns = {@JoinColumn(name = "id_direction")})
    private Map<String, Direction> directions = new HashMap<>();


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String rejectComment;
    @Enumerated(EnumType.STRING)
    private Status statusApp = Status.UPLOAD;
    private String mainInst;
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public Application() {
    }

    ;

    public Application(Map<String, Direction> directions) {
        this.directions = directions;
    }

}