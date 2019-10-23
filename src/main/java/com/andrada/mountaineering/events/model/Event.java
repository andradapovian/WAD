package com.andrada.mountaineering.events.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String location;

    @Column
    private Date startDate;

    @Column
    private Date endDate;
}
