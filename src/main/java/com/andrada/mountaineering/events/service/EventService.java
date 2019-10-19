package com.andrada.mountaineering.events.service;

import com.andrada.mountaineering.events.model.Event;
import com.andrada.mountaineering.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EventService {
    private EventRepository eventRepository;

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

    public Event getEvent(long id){
        return eventRepository.findById(id).orElse(null);
    }

    public Event updateEvent(long id, Event event){
        Event dbEvent=eventRepository.findById(id).orElse(null);
        if(Objects.isNull(dbEvent))
            return null;
        dbEvent.setName(event.getName());
        dbEvent.setLocation(event.getLocation());
        dbEvent.setStartDate(event.getStartDate());
        dbEvent.setEndDate(event.getEndDate());
        return eventRepository.save(dbEvent);
    }

    public void deleteEvent (long id){
        Event dbEvent = eventRepository.findById(id).orElse(null);
        if(Objects.isNull(dbEvent))
            return;
        eventRepository.delete(dbEvent);
    }
}
