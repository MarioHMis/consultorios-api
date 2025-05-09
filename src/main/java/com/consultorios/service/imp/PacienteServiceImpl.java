package com.consultorios.service.imp;

import com.consultorios.model.Paciente;
import com.consultorios.repository.PacienteRepository;
import com.consultorios.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> getPacienteById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente createPaciente(Paciente paciente) {
        if (pacienteRepository.existsByEmail(paciente.getEmail())) {
            throw new IllegalArgumentException("Paciente con este email ya existe.");
        }
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente updatePaciente(Long id, Paciente paciente) {
        Paciente existingPaciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado."));

        existingPaciente.setFirstName(paciente.getFirstName());
        existingPaciente.setLastName(paciente.getLastName());
        existingPaciente.setEmail(paciente.getEmail());
        existingPaciente.setPhone(paciente.getPhone());
        existingPaciente.setAddress(paciente.getAddress());
        existingPaciente.setDateOfBirth(paciente.getDateOfBirth());

        return pacienteRepository.save(existingPaciente);
    }

    @Override
    public void deletePaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Paciente no encontrado.");
        }
        pacienteRepository.deleteById(id);
    }
}
