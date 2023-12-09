package com.api.agendamento.controller;

import com.api.agendamento.dto.professional.ReadProfessionalDto;
import com.api.agendamento.dto.professional.RecordProfessionalDto;
import com.api.agendamento.service.ProfessionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("professional")
public class ProfessinalController {

    @Autowired
    private ProfessionalService professionalService;

    @GetMapping
    public ResponseEntity<List<ReadProfessionalDto>> getAllProfessionals(){
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.getAllProfessionals());
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadProfessionalDto> getProfessionalById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.getProfessionalById(id));
    }

    @PostMapping
    public ResponseEntity<ReadProfessionalDto> recordProfessional(@RequestBody @Valid RecordProfessionalDto recordProfessionalDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(professionalService.recordProfessional(recordProfessionalDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ReadProfessionalDto> updateProfessional(@PathVariable Long id, @RequestBody @Valid RecordProfessionalDto recordProfessionalDto){
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.updateProfessional(id, recordProfessionalDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProfessional(@PathVariable Long id){
        professionalService.deleteProfessional(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removed data.");
    }

}