package com.elpuig.conflict_tracker.repository;

import com.elpuig.conflict_tracker.model.Conflict;
import com.elpuig.conflict_tracker.model.enums.ConflictStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConflictRepository extends CrudRepository<Conflict, Long> {

    List<Conflict> findByDeletedFalse();
    List<Conflict> findByStatusAndDeletedFalse(ConflictStatus status);
    Optional<Conflict> findByIdAndDeletedFalse(Long id);

}