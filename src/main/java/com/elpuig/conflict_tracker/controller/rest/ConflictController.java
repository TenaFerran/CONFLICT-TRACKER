package com.elpuig.conflict_tracker.controller;

import com.elpuig.conflict_tracker.dto.ConflictDTO;
import com.elpuig.conflict_tracker.model.enums.ConflictStatus;
import com.elpuig.conflict_tracker.service.ConflictService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conflicts")
public class ConflictController {

    private final ConflictService conflictService;

    public ConflictController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    @GetMapping
    public List<ConflictDTO> getAll(@RequestParam(required = false) ConflictStatus status) {
        return (status == null)
                ? conflictService.getAllConflicts()
                : conflictService.getConflictsByStatus(status);
    }

    @GetMapping("/{id}")
    public ConflictDTO getById(@PathVariable Long id) {
        return conflictService.getConflict(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConflictDTO create(@RequestBody ConflictDTO dto) {
        return conflictService.addConflict(dto);
    }

    @PutMapping("/{id}")
    public ConflictDTO update(@PathVariable Long id, @RequestBody ConflictDTO dto) {
        return conflictService.updateConflict(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        conflictService.deleteConflict(id);
    }
}
