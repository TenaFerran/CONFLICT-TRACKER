package com.elpuig.conflict_tracker.mapper;

import com.elpuig.conflict_tracker.dto.CountryDTO;
import com.elpuig.conflict_tracker.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO toDTO(Country country);
    List<CountryDTO> toDTO(List<Country> countries);
    Country toEntity(CountryDTO countryDTO);
}
