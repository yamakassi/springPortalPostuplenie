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
    private String series;
    private String passportNumber;
    private String passportAuthor;
    private Date passportDate;
    private String passportCode;

    public Passport() {
    }

    ;

    public boolean isCheckRequiredFieldsFilled() {

        if (null == this.series || null == this.passportNumber || null == this.passportAuthor || null == this.passportCode) {
            return false;
        } else return true;
    }
}
