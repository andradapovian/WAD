package com.andrada.mountaineering.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String roles;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;
}
