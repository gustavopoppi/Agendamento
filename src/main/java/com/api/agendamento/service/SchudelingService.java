package com.api.agendamento.service;

import com.api.agendamento.dto.RecordSchudelingDto;
import com.api.agendamento.model.Schudeling;
import com.api.agendamento.repository.SchudelingRepository;
import org.springframework.beans.BeanUtils;
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

    public Schudeling getSchudelingById(Long id) {
        return schudelingByIdThrowIFIsEmpty(id);
    }

    public Schudeling createSchudeling(RecordSchudelingDto recordSchudelingDto) {
        Schudeling schudeling = new Schudeling();
        BeanUtils.copyProperties(recordSchudelingDto, schudeling);
        schudelingRepository.save(schudeling);
        return schudeling;
    }

    public Schudeling updateSchudeling(Long id, RecordSchudelingDto recordSchudelingDto) {
        Schudeling schudeling = schudelingByIdThrowIFIsEmpty(id);
        BeanUtils.copyProperties(recordSchudelingDto, schudeling);
        schudelingRepository.save(schudeling);

        return schudeling;
    }

    public void deleteSchudeling(Long id) {
        schudelingRepository.delete(schudelingByIdThrowIFIsEmpty(id));
    }

    private Schudeling schudelingByIdThrowIFIsEmpty(Long id){
        Optional<Schudeling> schudelingById = schudelingRepository.findById(id);

        if (schudelingById.isEmpty())
            throw new RuntimeException("Schudeling not found.");

        return schudelingById.get();
    }
}
