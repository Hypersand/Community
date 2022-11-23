package com.Together.Community.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_id")
    private Long id;
    @Column(name = "Role_name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
