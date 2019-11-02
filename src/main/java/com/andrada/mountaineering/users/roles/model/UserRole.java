package com.andrada.mountaineering.users.roles.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //schimba asa peste tot
    private  long id;

    @Column
    private String role;
}
