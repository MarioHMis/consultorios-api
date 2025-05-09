package com.consultorios.controller;

import com.consultorios.model.Medico;
import com.consultorios.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> getAllMedicos() {
        List<Medico> medicos = medicoService.getAllMedicos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable Long id) {
        return medicoService.getMedicoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico medico) {
        Medico createdMedico = medicoService.createMedico(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> updateMedico(
            @PathVariable Long id,
            @RequestBody Medico medico) {
        Medico updatedMedico = medicoService.updateMedico(id, medico);
        return ResponseEntity.ok(updatedMedico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.noContent().build();
    }
}

