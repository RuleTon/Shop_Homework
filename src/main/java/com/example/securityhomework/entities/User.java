package com.example.securityhomework.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "score")
    private String score;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


//   @ManyToMany
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Collection<Role> roles;
}
