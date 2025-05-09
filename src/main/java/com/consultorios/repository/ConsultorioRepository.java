package com.consultorios.repository;

import com.consultorios.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    boolean existsByName(String name);
}