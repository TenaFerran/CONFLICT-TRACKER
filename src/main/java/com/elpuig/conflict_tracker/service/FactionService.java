package com.elpuig.conflict_tracker.service;

import com.elpuig.conflict_tracker.dto.FactionDTO;
import com.elpuig.conflict_tracker.mapper.FactionMapper;
import com.elpuig.conflict_tracker.model.Event;
import com.elpuig.conflict_tracker.model.Faction;
import com.elpuig.conflict_tracker.repository.FactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactionService {

    private final FactionRepository factionRepository;
    private final FactionMapper factionMapper;

    public FactionService(FactionRepository factionRepository, FactionMapper factionMapper) {
        this.factionRepository = factionRepository;
        this.factionMapper = factionMapper;
    }

    public List<FactionDTO> getAllFactions() {
        List<Faction> factions = new ArrayList<>();
        factionRepository.findAll().forEach(factions::add);
        return factionMapper.toDTO(factions);
    }

    public FactionDTO getFactionById(Long id) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found with id " + id));
        return factionMapper.toDTO(faction);
    }

    public FactionDTO createFaction(FactionDTO dto) {
        Faction entity = factionMapper.toEntity(dto);

        Faction saved = factionRepository.save(entity);
        return factionMapper.toDTO(saved);
    }

    public FactionDTO updateFaction(Long id, FactionDTO dto) {
        Faction existing = factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found with id " + id));

        existing.setName(dto.name());

        Faction saved = factionRepository.save(existing);
        return factionMapper.toDTO(saved);
    }

    public void deleteFaction(Long id) {
        if (!factionRepository.existsById(id)) {
            throw new RuntimeException("Faction not found with id " + id);
        }
        factionRepository.deleteById(id);
    }
}
