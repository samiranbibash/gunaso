package com.envisionnepal.gunasho.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)

    private String email;


    private String password;
    private String role;
    private boolean enabled;
    private String imageUrl;

    @Column(length = 500)
    private String about;


//    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<AdminNotification> adminNotification = new ArrayList<>();

}

