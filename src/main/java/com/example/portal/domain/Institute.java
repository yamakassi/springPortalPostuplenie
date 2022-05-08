package com.example.portal.domain;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Institute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@Column(unique=true)
    private String title;
    private String abbr;
    @Column(columnDefinition = "text")
    private String description;
    private String director;
    private String contacts;

    /*  @OneToMany(cascade = CascadeType.ALL, mappedBy = "institute")
      @ToString.Exclude
      private List<Direction> directions;*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Institute that = (Institute) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}