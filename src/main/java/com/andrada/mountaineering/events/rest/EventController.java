package com.andrada.mountaineering.events.rest;

import com.andrada.mountaineering.events.model.Event;
import com.andrada.mountaineering.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService=eventService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Event createNewEvent(@RequestBody Event event){
        return eventService.createNewEvent(event);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEvent(@PathVariable long id){
        return eventService.getEvent(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Event updateEvent(@PathVariable long id, @RequestBody Event event){
        return eventService.updateEvent(id,event);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEvent(@PathVariable long id){
        eventService.deleteEvent(id);
    }
}
