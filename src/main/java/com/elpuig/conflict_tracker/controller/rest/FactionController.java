package com.elpuig.conflict_tracker.controller.rest;

import com.elpuig.conflict_tracker.dto.FactionDTO;
import com.elpuig.conflict_tracker.service.FactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/factions")
public class FactionController {

    private final FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @GetMapping
    public List<FactionDTO> getAllFactions() {
        return factionService.getAllFactions();
    }

    @GetMapping("/{id}")
    public FactionDTO getFactionById(@PathVariable Long id) {
        return factionService.getFactionById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FactionDTO createFaction(@RequestBody FactionDTO dto) {
        return factionService.createFaction(dto);
    }

    @PutMapping("/{id}")
    public FactionDTO updateFaction(@PathVariable Long id, @RequestBody FactionDTO dto) {
        return factionService.updateFaction(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFaction(@PathVariable Long id) {
        factionService.deleteFaction(id);
    }
}
