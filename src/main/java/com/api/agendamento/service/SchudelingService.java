package com.api.agendamento.service;

import com.api.agendamento.model.Schudeling;
import com.api.agendamento.repository.SchudelingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchudelingService {

    private SchudelingRepository schudelingRepository;

    public SchudelingService(SchudelingRepository schudelingRepository) {
        this.schudelingRepository = schudelingRepository;
    }

    public List<Schudeling> getAllSchudelings() {
        return schudelingRepository.findAll();
    }

    public Optional<Schudeling> getSchudelingById(Long id) {
        return schudelingRepository.findById(id);
    }
}
