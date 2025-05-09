package com.consultorios.controller;

import com.consultorios.model.Cita;
import com.consultorios.model.Consultorio;
import com.consultorios.model.Medico;
import com.consultorios.model.Paciente;
import com.consultorios.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas() {
        List<Cita> citas = citaService.getAllCitas();
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Long id) {
        return citaService.getCitaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        Cita createdCita = citaService.createCita(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(
            @PathVariable Long id,
            @RequestBody Cita cita) {
        Cita updatedCita = citaService.updateCita(id, cita);
        return ResponseEntity.ok(updatedCita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Cita>> getCitasByMedico(@PathVariable Long medicoId) {
        Medico medico = new Medico();
        medico.setId(medicoId);
        List<Cita> citas = citaService.getCitasByMedico(medico);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Cita>> getCitasByPaciente(@PathVariable Long pacienteId) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteId);
        List<Cita> citas = citaService.getCitasByPaciente(paciente);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/consultorio/{consultorioId}")
    public ResponseEntity<List<Cita>> getCitasByConsultorio(@PathVariable Long consultorioId) {
        Consultorio consultorio = new Consultorio();
        consultorio.setId(consultorioId);
        List<Cita> citas = citaService.getCitasByConsultorio(consultorio);
        return ResponseEntity.ok(citas);
    }
}

