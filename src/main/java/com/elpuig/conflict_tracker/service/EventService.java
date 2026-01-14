package com.elpuig.conflict_tracker.service;

import com.elpuig.conflict_tracker.dto.EventDTO;
import com.elpuig.conflict_tracker.mapper.EventMapper;
import com.elpuig.conflict_tracker.model.Conflict;
import com.elpuig.conflict_tracker.model.Event;
import com.elpuig.conflict_tracker.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = new ArrayList<>();

        eventRepository.findAll().forEach(events::add);
        return eventMapper.toDTO(events);
    }

    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));
        return eventMapper.toDTO(event);
    }

    public EventDTO createEvent(EventDTO dto) {
        Event entity = eventMapper.toEntity(dto);

        Event saved = eventRepository.save(entity);
        return eventMapper.toDTO(saved);
    }

    public EventDTO updateEvent(Long id, EventDTO dto) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));

        existing.setEventDate(dto.eventDate());
        existing.setLocation(dto.location());
        existing.setDescription(dto.description());

        Event saved = eventRepository.save(existing);
        return eventMapper.toDTO(saved);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id " + id);
        }
        eventRepository.deleteById(id);
    }
}
