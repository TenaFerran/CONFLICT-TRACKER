package com.elpuig.conflict_tracker.repository;

import com.elpuig.conflict_tracker.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    Event findById(long id);
    List<Event> findByEventDate(LocalDate date);
    List<Event> findByLocation (String location);

}
