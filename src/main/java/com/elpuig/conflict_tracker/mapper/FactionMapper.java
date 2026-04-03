package com.elpuig.conflict_tracker.mapper;

import com.elpuig.conflict_tracker.dto.EventDTO;
import com.elpuig.conflict_tracker.dto.FactionDTO;
import com.elpuig.conflict_tracker.model.Event;
import com.elpuig.conflict_tracker.model.Faction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FactionMapper {
    FactionDTO toDTO(Faction faction);
    List<FactionDTO> toDTO(List<Faction> factions);
    Faction toEntity(FactionDTO factionDTO);
}
