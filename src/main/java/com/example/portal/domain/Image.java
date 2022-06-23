package com.example.portal.domain;

import com.example.portal.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "originalFileName")
    private String originalFileName;
    private Long size;
    private String type;
    private String comment;
    @Column(length = 10000)
    private byte[] bytes;

    @OneToOne(mappedBy = "image")
    private Institute institute;
    @Column(name = "dateCreated")
    private LocalDateTime dateCreated;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    private void init() {
        dateCreated = LocalDateTime.now();
    }


}
