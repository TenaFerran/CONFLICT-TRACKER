package com.elpuig.conflict_tracker.service;

import com.elpuig.conflict_tracker.dto.ConflictDTO;
import com.elpuig.conflict_tracker.mapper.ConflictMapper;
import com.elpuig.conflict_tracker.model.Conflict;
import com.elpuig.conflict_tracker.model.Country;
import com.elpuig.conflict_tracker.model.enums.ConflictStatus;
import com.elpuig.conflict_tracker.repository.ConflictRepository;
import com.elpuig.conflict_tracker.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ConflictService {

    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;
    private final ConflictMapper conflictMapper;

    public ConflictService(ConflictRepository conflictRepository,
                           CountryRepository countryRepository,
                           ConflictMapper conflictMapper) {
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
        this.conflictMapper = conflictMapper;
    }

    public List<ConflictDTO> getAllConflicts() {
        return conflictMapper.toDTO(conflictRepository.findByDeletedFalse());
    }

    public List<ConflictDTO> getConflictsByStatus(ConflictStatus status) {
        return conflictMapper.toDTO(conflictRepository.findByStatusAndDeletedFalse(status));
    }

    public ConflictDTO getConflict(Long id) {
        Conflict conflict = conflictRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Conflict with id " + id + " not found"));
        return conflictMapper.toDTO(conflict);
    }

    public ConflictDTO addConflict(ConflictDTO conflictDTO) {
        Conflict conflict = conflictMapper.toEntity(conflictDTO);

        List<Country> countries = new ArrayList<>();
        countryRepository.findAllById(conflictDTO.countryIds()).forEach(countries::add);
        conflict.setCountries(countries);

        Conflict saved = conflictRepository.save(conflict);
        return conflictMapper.toDTO(saved);
    }

    public ConflictDTO updateConflict(Long id, ConflictDTO conflictDTO) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conflict not found with id " + id));

        if (conflict.isDeleted()) {
            throw new RuntimeException("No se puede actualizar un conflicto eliminado");
        }

        conflict.setName(conflictDTO.name());
        conflict.setStartDate(conflictDTO.startDate());
        conflict.setStatus(conflictDTO.status());
        conflict.setDescription(conflictDTO.description());

        List<Country> countries = new ArrayList<>();
        countryRepository.findAllById(conflictDTO.countryIds()).forEach(countries::add);
        conflict.setCountries(countries);

        Conflict saved = conflictRepository.save(conflict);
        return conflictMapper.toDTO(saved);
    }

    public void deleteConflict(Long id) {
        Conflict conflict = conflictRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Conflict not found with id " + id));

        conflict.setDeleted(true);
        conflictRepository.save(conflict);
    }
}