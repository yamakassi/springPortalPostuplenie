package com.example.portal.domain;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String abbr;
    private String title;
/*
    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;
*/

}
