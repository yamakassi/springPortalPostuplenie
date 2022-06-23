package com.example.portal.domain.users;

import com.example.portal.domain.Application;
import com.example.portal.domain.Exam;
import com.example.portal.domain.Image;
import com.example.portal.domain.enums.Role;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true)
    private String email;


    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Exam> exams = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info_id")
    private PersonalInfo personalInfo;
    @Column(columnDefinition = "text")
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    private LocalDateTime dateOfCreated;
    //info user
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Application application;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Passport passport;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CurrentEducation currentEducation;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Contacts contacts;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AddInfo additInfo;
    private boolean spravka;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();
    private String departament;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public boolean fullCheckInfoField() {
        if (personalInfo == null && personalInfo.isCheckRequiredFieldsFilled() == false
                && passport == null && passport.isCheckRequiredFieldsFilled() == false
                && currentEducation == null && currentEducation.isCheckRequiredFieldsFilled() == false
                && contacts == null && contacts.isCheckRequiredFieldsFilled() == false
        ) {
            return false;
        } else return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public void addFiles(Image file) {
        images.add(file);

    }
}
