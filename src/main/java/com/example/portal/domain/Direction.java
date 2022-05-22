package com.example.portal.domain;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String abbr;
    private String title;
    @ManyToMany(mappedBy = "directions")
    private Set<Application> applications = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private  Institute institute;


    public void addApplication(Application appl) {
        this.applications.add(appl);

    }

    public void removeApplication(Application appl) {
        this.applications.remove(appl);
        appl.getDirections().remove(this);
    }

    @Override
    public String toString() {
        return "Direction{" +
                "id=" + id +
                ", abbr='" + abbr + '\'' +
                ", title='" + title + '\'' +
                ", applications=" + applications +
                ", institute=" + institute.getAbbr() +

                '}';
    }



}
