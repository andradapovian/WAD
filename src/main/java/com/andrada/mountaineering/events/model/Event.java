package com.andrada.mountaineering.events.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
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

    @Column
    private String details;
}
