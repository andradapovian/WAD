package com.andrada.mountaineering.users.model;

import com.andrada.mountaineering.users.roles.model.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table( name = "person")

public class User {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable
    private Set<UserRole> roles;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String username;
}
