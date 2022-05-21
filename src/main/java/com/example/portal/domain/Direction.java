package com.example.portal.domain;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "direction")
    private List<Application> applications = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private  Institute institute;

    @Override
    public String toString() {
        return "Direction{" +
                "id=" + id +
                ", abbr='" + abbr + '\'' +
                ", title='" + title + '\'' +
                ", applications=" + applications +
                '}';
    }

/*
    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;
*/

}
