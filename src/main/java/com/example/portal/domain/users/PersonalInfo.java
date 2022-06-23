package com.example.portal.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    private String patronymic;
    private int sex;

    /*  private Date birthDate;*/
    private String birthPlace;
    private int countryOfBirth;
    private int nationality;
    private boolean hasRussianNationality;
    private int countryId;
    private String snils;


    public boolean isCheckRequiredFieldsFilled() {

        if (null == this.name || null == this.surname || this.countryId == -1 || this.nationality == -1
                || this.countryOfBirth == -1 || this.countryOfBirth == -1 || null == this.snils) {
            return false;
        }

        return true;
    }
}
