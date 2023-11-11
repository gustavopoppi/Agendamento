package com.api.agendamento.repository;

import com.api.agendamento.model.Schudeling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchudelingRepository extends JpaRepository<Schudeling, Long> {
}
