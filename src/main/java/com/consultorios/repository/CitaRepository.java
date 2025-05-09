package com.consultorios.repository;


import com.consultorios.model.Cita;
import com.consultorios.model.Consultorio;
import com.consultorios.model.Medico;
import com.consultorios.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Listar citas por médico
    List<Cita> findByMedico(Medico medico);

    // Listar citas por paciente
    List<Cita> findByPaciente(Paciente paciente);

    // Listar citas por consultorio
    List<Cita> findByConsultorio(Consultorio consultorio);

    // Verificar si una cita existe en un horario específico
    boolean existsByMedicoAndAppointmentDateTime(Medico medico, LocalDateTime appointmentDateTime);
}
