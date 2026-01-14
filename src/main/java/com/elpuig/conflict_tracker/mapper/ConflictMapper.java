package com.elpuig.conflict_tracker.mapper;

import com.elpuig.conflict_tracker.dto.ConflictDTO;
import com.elpuig.conflict_tracker.model.Conflict;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ConflictMapper {
    ConflictDTO toDTO(Conflict conflict);
    List<ConflictDTO> toDTO(List<Conflict> conflicts);
    Conflict toEntity(ConflictDTO conflictDTO);
}
