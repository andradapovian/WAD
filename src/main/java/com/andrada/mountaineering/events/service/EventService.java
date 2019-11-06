package com.andrada.mountaineering.events.service;

import com.andrada.mountaineering.events.model.Event;
import com.andrada.mountaineering.events.repository.EventRepository;
import com.andrada.mountaineering.events.rest.EventController;
import com.andrada.mountaineering.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Objects;

@Service
public class EventService {
    private EventRepository eventRepository;
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createNewEvent(Event event){
        return eventRepository.save(event);
    }

    public Event getEvent (long id) throws EntityNotFoundException{
        return eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found at ID: " + id));// this everywhere
    }

    public Event updateEvent(long id, Event event) throws EntityNotFoundException{
        Event dbEvent=eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found at ID "+ id));
        if(Objects.isNull(dbEvent))
            return null;
        dbEvent.setName(event.getName());
        dbEvent.setLocation(event.getLocation());
        dbEvent.setStartDate(event.getStartDate());
        dbEvent.setEndDate(event.getEndDate());
        return eventRepository.save(dbEvent);
    }

    public void deleteEvent (long id) throws EntityNotFoundException{
        Event dbEvent = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found at ID "+ id));
        if(Objects.isNull(dbEvent))
            return;
        eventRepository.delete(dbEvent);
    }

}
