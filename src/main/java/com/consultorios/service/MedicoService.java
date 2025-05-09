package com.consultorios.service;

import com.consultorios.model.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoService {
    List<Medico> getAllMedicos();
    Optional<Medico> getMedicoById(Long id);
    Medico createMedico(Medico medico);
    Medico updateMedico(Long id, Medico medico);
    void deleteMedico(Long id);
}