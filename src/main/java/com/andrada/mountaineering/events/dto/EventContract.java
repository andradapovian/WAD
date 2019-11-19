package com.andrada.mountaineering.events.dto;

import com.andrada.mountaineering.events.model.Event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventContract implements Serializable {

    private long id;
    private String name;
    private String location;
    private Date startDate;
    private Date endDate;
    private String details;

    public static EventContract of(Event event){
        EventContract target = new EventContract();
        BeanUtils.copyProperties(event, target);
        return target;
    }

}
