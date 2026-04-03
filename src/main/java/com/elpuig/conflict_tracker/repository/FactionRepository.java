package com.elpuig.conflict_tracker.repository;

import com.elpuig.conflict_tracker.model.Faction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionRepository extends CrudRepository<Faction, Long> {

    Faction findById(long id);
    Faction findByName(String id);

}
