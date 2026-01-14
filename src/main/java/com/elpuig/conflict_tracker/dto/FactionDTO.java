package com.elpuig.conflict_tracker.dto;

import com.elpuig.conflict_tracker.model.Conflict;
import com.elpuig.conflict_tracker.model.Country;

import java.util.List;

public record FactionDTO (Long id, String name, List<Long> countryIds){
}
