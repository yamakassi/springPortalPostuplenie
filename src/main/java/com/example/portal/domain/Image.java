package com.example.portal.domain;

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


    private byte[] bytes;

    @OneToOne(mappedBy = "image")
    private Institute institute;
    private LocalDateTime dateCreated;

    @PrePersist
    private void init(){
        dateCreated = LocalDateTime.now();
    }


}
