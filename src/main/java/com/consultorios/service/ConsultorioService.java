package com.consultorios.service;

import com.consultorios.model.Consultorio;

import java.util.List;
import java.util.Optional;

public interface ConsultorioService {
    List<Consultorio> getAllConsultorios();
    Optional<Consultorio> getConsultorioById(Long id);
    Consultorio createConsultorio(Consultorio consultorio);
    Consultorio updateConsultorio(Long id, Consultorio consultorio);
    void deleteConsultorio(Long id);
}