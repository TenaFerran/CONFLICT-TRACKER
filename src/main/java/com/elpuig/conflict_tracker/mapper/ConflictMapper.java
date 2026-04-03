package com.elpuig.conflict_tracker.mapper;

import com.elpuig.conflict_tracker.dto.ConflictDTO;
import com.elpuig.conflict_tracker.model.Conflict;
import com.elpuig.conflict_tracker.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConflictMapper {

    @Mapping(target = "countryIds", source = "countries")
    ConflictDTO toDTO(Conflict conflict);

    List<ConflictDTO> toDTO(List<Conflict> conflicts);

    @Mapping(target = "countries", ignore = true)
    Conflict toEntity(ConflictDTO conflictDTO);

    default Long map(Country country) {
        return country != null ? country.getId() : null;
    }
}