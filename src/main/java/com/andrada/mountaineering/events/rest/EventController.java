package com.andrada.mountaineering.events.rest;

import com.andrada.mountaineering.events.model.Event;
import com.andrada.mountaineering.events.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    public Event getEvent(@PathVariable long id) throws Exception{
        return eventService.getEvent(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Event updateEvent(@PathVariable long id, @RequestBody Event event) throws Exception{
        return eventService.updateEvent(id,event);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEvent(@PathVariable long id) throws Exception{
        eventService.deleteEvent(id);
    }



}
