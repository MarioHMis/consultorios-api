package com.consultorios.service.imp;


import com.consultorios.model.Medico;
import com.consultorios.repository.MedicoRepository;
import com.consultorios.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;

    @Override
    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public Optional<Medico> getMedicoById(Long id) {
        return medicoRepository.findById(id);
    }

    @Override
    public Medico createMedico(Medico medico) {
        if (medicoRepository.existsByLicenseNumber(medico.getLicenseNumber())) {
            throw new IllegalArgumentException("El Medico con este número de licencia ya existe.");
        }
        return medicoRepository.save(medico);
    }

    @Override
    public Medico updateMedico(Long id, Medico medico) {
        Medico existingMedico = medicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medico no encontrado."));

        existingMedico.setFirstName(medico.getFirstName());
        existingMedico.setLastName(medico.getLastName());
        existingMedico.setLicenseNumber(medico.getLicenseNumber());
        existingMedico.setSpecialty(medico.getSpecialty());
        existingMedico.setPhone(medico.getPhone());
        return medicoRepository.save(existingMedico);
    }

    @Override
    public void deleteMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new IllegalArgumentException("Medico no encontrado.");
        }
        medicoRepository.deleteById(id);
    }
}