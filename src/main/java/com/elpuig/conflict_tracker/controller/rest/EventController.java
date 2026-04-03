package com.elpuig.conflict_tracker.controller.rest;

import com.elpuig.conflict_tracker.dto.EventDTO;
import com.elpuig.conflict_tracker.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDTO getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDTO createEvent(@RequestBody EventDTO dto) {
        return eventService.createEvent(dto);
    }

    @PutMapping("/{id}")
    public EventDTO updateEvent(@PathVariable Long id, @RequestBody EventDTO dto) {
        return eventService.updateEvent(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
