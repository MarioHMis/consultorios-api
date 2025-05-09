package com.consultorios.service.imp;
import com.consultorios.model.Consultorio;
import com.consultorios.repository.ConsultorioRepository;
import com.consultorios.service.ConsultorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultorioServiceImpl implements ConsultorioService {

    private final ConsultorioRepository consultorioRepository;

    @Override
    public List<Consultorio> getAllConsultorios() {
        return consultorioRepository.findAll();
    }

    @Override
    public Optional<Consultorio> getConsultorioById(Long id) {
        return consultorioRepository.findById(id);
    }

    @Override
    public Consultorio createConsultorio(Consultorio consultorio) {
        if (consultorioRepository.existsByName(consultorio.getName())) {
            throw new IllegalArgumentException("Nombre de consultorio ya existe.");
        }
        return consultorioRepository.save(consultorio);
    }

    @Override
    public Consultorio updateConsultorio(Long id, Consultorio consultorio) {
        Consultorio existingConsultorio = consultorioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consultorio no encontrado."));

        existingConsultorio.setName(consultorio.getName());
        existingConsultorio.setAddress(consultorio.getAddress());
        existingConsultorio.setPhone(consultorio.getPhone());
        return consultorioRepository.save(existingConsultorio);
    }

    @Override
    public void deleteConsultorio(Long id) {
        if (!consultorioRepository.existsById(id)) {
            throw new IllegalArgumentException("Consultorio no encontrado.");
        }
        consultorioRepository.deleteById(id);
    }
}