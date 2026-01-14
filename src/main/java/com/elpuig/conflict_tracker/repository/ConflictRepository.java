package com.elpuig.conflict_tracker.repository;

import com.elpuig.conflict_tracker.model.Conflict;
import com.elpuig.conflict_tracker.model.enums.ConflictStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConflictRepository extends JpaRepository<Conflict, Long> {

    List<Conflict> findByStatus(ConflictStatus status);
    List<Conflict> findByStartDate(LocalDate startDate);
}
