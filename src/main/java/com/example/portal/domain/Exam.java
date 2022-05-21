package com.example.portal.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameExam;
    private int valueExam;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private  User user;

    public Exam(String nameExam, Integer valueExam,User user) {
        this.nameExam=nameExam;
        this.valueExam=valueExam;
        this.user= user ;

    }

    public Exam() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Exam exam = (Exam) o;
        return id != null && Objects.equals(id, exam.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", nameExam='" + nameExam + '\'' +
                ", valueExam=" + valueExam +
                '}';
    }
}
