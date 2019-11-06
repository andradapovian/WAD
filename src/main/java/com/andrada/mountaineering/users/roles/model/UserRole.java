package com.andrada.mountaineering.users.roles.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue
    private  long id;

    @Column
    private String role;
}
