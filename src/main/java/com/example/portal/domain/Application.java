package com.example.portal.domain;

import com.example.portal.domain.users.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String snils;
    @ManyToMany
    @JoinTable(name = "direction_application",
            joinColumns = { @JoinColumn(name = "id_application") },
            inverseJoinColumns = { @JoinColumn(name = "id_direction") })
    private Set<Direction> directions = new HashSet<>();



    @OneToOne(mappedBy = "application")
    private User user;
    private boolean registered = false;
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

}
