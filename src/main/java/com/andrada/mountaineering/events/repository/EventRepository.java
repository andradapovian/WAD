package com.andrada.mountaineering.events.repository;

import com.andrada.mountaineering.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
