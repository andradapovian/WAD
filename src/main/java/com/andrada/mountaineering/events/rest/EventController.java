package com.andrada.mountaineering.events.rest;

import com.andrada.mountaineering.events.dto.EventContract;
import com.andrada.mountaineering.events.model.Event;
import com.andrada.mountaineering.events.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")

public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService=eventService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventContract> getAllEvents(){
        return eventService.getAllEvents().stream()
                .map(EventContract::of)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventContract createNewEvent(@RequestBody Event event){
        return EventContract.of(eventService.createNewEvent(event));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EventContract getEvent(@PathVariable long id) throws Exception{
        return EventContract.of(eventService.getEvent(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EventContract updateEvent(@PathVariable long id, @RequestBody Event event) throws Exception{
        return EventContract.of(eventService.updateEvent(id,event));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEvent(@PathVariable long id) throws Exception{
        eventService.deleteEvent(id);
    }



}
