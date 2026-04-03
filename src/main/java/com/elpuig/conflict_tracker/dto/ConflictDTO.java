package com.elpuig.conflict_tracker.dto;

import com.elpuig.conflict_tracker.model.enums.ConflictStatus;

import java.time.LocalDate;
import java.util.List;


public record ConflictDTO(
        Long id,
        String name,
        LocalDate startDate,
        ConflictStatus status,
        String description,
        List<Long> countryIds
) {}
