package com.example.portal.domain.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int series;
    private int passportNumber;
    private String passportAuthor;
    private Date passportDate;
    private int passportCode;
    private String snils;
}
