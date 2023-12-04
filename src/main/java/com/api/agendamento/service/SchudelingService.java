package com.api.agendamento.service;

import com.api.agendamento.dto.schudeling.ReadSchudelingDto;
import com.api.agendamento.dto.schudeling.RecordSchudelingDto;
import com.api.agendamento.model.Schudeling;
import com.api.agendamento.repository.SchudelingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchudelingService {

    private final SchudelingRepository schudelingRepository;

    public SchudelingService(SchudelingRepository schudelingRepository) {
        this.schudelingRepository = schudelingRepository;
    }

    public List<ReadSchudelingDto> getAllSchudelings() {
        List<Schudeling> schudelings = schudelingRepository.findAll();
        return schudelings.stream().map(this::mapSchudelingToReadSchudelingDto).toList();
    }

    public ReadSchudelingDto getSchudelingById(Long id) {
        return mapSchudelingToReadSchudelingDto(schudelingByIdThrowIFIsEmpty(id));
    }

    public ReadSchudelingDto recordSchudeling(RecordSchudelingDto recordSchudelingDto) {
        return addAndSaveSchudeling(new Schudeling(), recordSchudelingDto);
    }

    public ReadSchudelingDto updateSchudeling(Long id, RecordSchudelingDto recordSchudelingDto) {
        return addAndSaveSchudeling(schudelingByIdThrowIFIsEmpty(id), recordSchudelingDto);
    }

    public void deleteSchudeling(Long id) {
        schudelingRepository.deleteById(id);
    }

    private Schudeling schudelingByIdThrowIFIsEmpty(Long id){
        Optional<Schudeling> schudelingById = schudelingRepository.findById(id);

        if (schudelingById.isEmpty())
            throw new RuntimeException("Schudeling not found.");

        return schudelingById.get();
    }

    private ReadSchudelingDto mapSchudelingToReadSchudelingDto(Schudeling schudeling) {
        return new ReadSchudelingDto(schudeling.getId(), schudeling.getData(), schudeling.getCustomer(), schudeling.getProfessional());
    }

    private ReadSchudelingDto addAndSaveSchudeling(Schudeling schudeling, RecordSchudelingDto recordSchudelingDto) {
        BeanUtils.copyProperties(recordSchudelingDto, schudeling);
        schudelingRepository.save(schudeling);
        return mapSchudelingToReadSchudelingDto(schudeling);
    }
}
