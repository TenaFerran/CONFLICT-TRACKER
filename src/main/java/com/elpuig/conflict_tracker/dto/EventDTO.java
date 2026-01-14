package com.elpuig.conflict_tracker.dto;
import java.time.LocalDate;


public record EventDTO (Long id, LocalDate eventDate, String location, String description, Long conflictId){
}
