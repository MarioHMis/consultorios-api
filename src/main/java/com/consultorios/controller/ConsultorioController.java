package com.consultorios.controller;

import com.consultorios.model.Consultorio;
import com.consultorios.service.ConsultorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultorios")
@RequiredArgsConstructor
public class ConsultorioController {

    private final ConsultorioService consultorioService;

    @GetMapping
    public ResponseEntity<List<Consultorio>> getAllConsultorios() {
        List<Consultorio> consultorios = consultorioService.getAllConsultorios();
        return ResponseEntity.ok(consultorios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultorio> getConsultorioById(@PathVariable Long id) {
        return consultorioService.getConsultorioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Consultorio> createConsultorio(@RequestBody Consultorio consultorio) {
        Consultorio createdConsultorio = consultorioService.createConsultorio(consultorio);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConsultorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultorio> updateConsultorio(
            @PathVariable Long id,
            @RequestBody Consultorio consultorio) {
        Consultorio updatedConsultorio = consultorioService.updateConsultorio(id, consultorio);
        return ResponseEntity.ok(updatedConsultorio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultorio(@PathVariable Long id) {
        consultorioService.deleteConsultorio(id);
        return ResponseEntity.noContent().build();
    }
}


