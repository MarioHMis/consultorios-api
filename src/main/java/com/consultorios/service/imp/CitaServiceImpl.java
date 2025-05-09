package com.consultorios.service.imp;

import com.consultorios.model.Cita;
import com.consultorios.model.Consultorio;
import com.consultorios.model.Medico;
import com.consultorios.model.Paciente;
import com.consultorios.repository.CitaRepository;
import com.consultorios.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    @Override
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    @Override
    public Optional<Cita> getCitaById(Long id) {
        return citaRepository.findById(id);
    }

    @Override
    public Cita createCita(Cita cita) {
        if (citaRepository.existsByMedicoAndAppointmentDateTime(
                cita.getMedico(), cita.getAppointmentDateTime())) {
            throw new IllegalArgumentException("Medico already has an appointment at this time.");
        }
        return citaRepository.save(cita);
    }

    @Override
    public Cita updateCita(Long id, Cita cita) {
        Cita existingCita = citaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cita not found."));

        existingCita.setMedico(cita.getMedico());
        existingCita.setPaciente(cita.getPaciente());
        existingCita.setConsultorio(cita.getConsultorio());
        existingCita.setAppointmentDateTime(cita.getAppointmentDateTime());
        existingCita.setStatus(cita.getStatus());

        return citaRepository.save(existingCita);
    }

    @Override
    public void deleteCita(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new IllegalArgumentException("Cita not found.");
        }
        citaRepository.deleteById(id);
    }

    @Override
    public List<Cita> getCitasByMedico(Medico medico) {
        return citaRepository.findByMedico(medico);
    }

    @Override
    public List<Cita> getCitasByPaciente(Paciente paciente) {
        return citaRepository.findByPaciente(paciente);
    }

    @Override
    public List<Cita> getCitasByConsultorio(Consultorio consultorio) {
        return citaRepository.findByConsultorio(consultorio);
    }
}