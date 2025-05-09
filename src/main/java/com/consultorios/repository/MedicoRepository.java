package com.consultorios.repository;

import com.consultorios.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsByLicenseNumber(String licenseNumber);
}