package com.Together.Community.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    private Long id;
    @Column(name = "User_password")
    private String password;
    @Column(name = "User_name")
    private String name;
    @Column(name = "User_nickname")
    private String nickname;
    @Column(name = "User_phoneNumber")
    private String phoneNumber;
    @Column(name = "User_profile")
    private String profile;
    @Column(name = "User_temperature")
    private double temperature;
    @Column(name = "User_enabled")
    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "User_role",
            joinColumns = @JoinColumn(name = "User_role_userid"),
            inverseJoinColumns = @JoinColumn(name = "User_role_roleid")
    )
    private List<Role> roles = new ArrayList<>();



}
