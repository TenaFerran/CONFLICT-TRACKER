package com.elpuig.conflict_tracker.repository;

import com.elpuig.conflict_tracker.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findById(long id);
    List<Country> findByName(String name);

}
