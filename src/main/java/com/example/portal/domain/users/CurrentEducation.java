package com.example.portal.domain.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class CurrentEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeSchool;

    private String schoolName;
    private int countryEducId;
    private int regionEducId;
    private String schoolCity;
    private int schoolExitYear;
    private String number;
    private String series;
    private double avgMark;
    private boolean isExcellent;

    public boolean isCheckRequiredFieldsFilled() {

        if (null == this.schoolName || this.countryEducId == -1 || this.regionEducId == -1 || null == this.schoolCity) {
            return false;
        } else return true;
    }
}
