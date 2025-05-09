package com.consultorios.service;

import com.consultorios.model.Cita;
import com.consultorios.model.Consultorio;
import com.consultorios.model.Medico;
import com.consultorios.model.Paciente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<Cita> getAllCitas();
    Optional<Cita> getCitaById(Long id);
    Cita createCita(Cita cita);
    Cita updateCita(Long id, Cita cita);
    void deleteCita(Long id);
    List<Cita> getCitasByMedico(Medico medico);
    List<Cita> getCitasByPaciente(Paciente paciente);
    List<Cita> getCitasByConsultorio(Consultorio consultorio);
}