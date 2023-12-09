package com.api.agendamento.service;

import com.api.agendamento.dto.customer.ReadCustomerDto;
import com.api.agendamento.dto.professional.ReadProfessionalDto;
import com.api.agendamento.dto.professional.RecordProfessionalDto;
import com.api.agendamento.model.Customer;
import com.api.agendamento.model.Professional;
import com.api.agendamento.repository.ProfessionalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;

    public ProfessionalService(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Transactional(readOnly = true)
    public List<ReadProfessionalDto> getAllProfessionals() {
        List<Professional> professionals = professionalRepository.findAll();
        return professionals.stream().map(this::mapProfessionalToReadProfessionalDto).toList();
    }

    @Transactional(readOnly = true)
    public ReadProfessionalDto getProfessionalById(Long id) {
        return mapProfessionalToReadProfessionalDto(professionalByIdThrowIFIsEmpty(id));
    }

    @Transactional
    public ReadProfessionalDto recordProfessional(RecordProfessionalDto recordProfessionalDto) {
        return addAndSaveProfessional(new Professional(), recordProfessionalDto);
    }

    @Transactional
    public void deleteProfessional(Long id) {
        professionalRepository.deleteById(id);
    }

    @Transactional
    public ReadProfessionalDto updateProfessional(Long id, RecordProfessionalDto recordProfessionalDto) {
        return addAndSaveProfessional(professionalByIdThrowIFIsEmpty(id), recordProfessionalDto);
    }

    private Professional professionalByIdThrowIFIsEmpty(Long id) {
        Optional<Professional> professionalById = professionalRepository.findById(id);
        if (professionalById.isEmpty())
            throw new RuntimeException("Professional not found.");

        return professionalById.get();
    }

    private ReadProfessionalDto addAndSaveProfessional(Professional professional, RecordProfessionalDto recordProfessionalDto) {
        BeanUtils.copyProperties(recordProfessionalDto, professional);
        professionalRepository.save(professional);
        return mapProfessionalToReadProfessionalDto(professional);
    }

    private ReadProfessionalDto mapProfessionalToReadProfessionalDto(Professional professional) {
        return new ReadProfessionalDto(professional.getId(), professional.getNome(), professional.getEmail(), professional.getTelefone());
    }
}