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
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mainPhoneNumber;
    private String secondPhoneNumber;
    private String addEmail;
    private String regionId;
    private String city;
    private String street;
    private String house;
    private int flat;
    private String postIndex;
    private boolean hostelAbit;

    public boolean isCheckRequiredFieldsFilled() {

        if (null == this.mainPhoneNumber || null == this.regionId || null == this.city) {
            return false;
        }

        return true;
    }
}
