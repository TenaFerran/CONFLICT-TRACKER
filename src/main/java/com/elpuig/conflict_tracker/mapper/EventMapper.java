package com.elpuig.conflict_tracker.mapper;

import com.elpuig.conflict_tracker.dto.EventDTO;
import com.elpuig.conflict_tracker.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO toDTO(Event event);
    List<EventDTO> toDTO(List<Event> events);
    Event toEntity(EventDTO eventDTO);

}
