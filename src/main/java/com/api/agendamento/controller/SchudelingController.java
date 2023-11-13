package com.api.agendamento.controller;

import com.api.agendamento.dto.RecordSchudelingDto;
import com.api.agendamento.model.Schudeling;
import com.api.agendamento.service.SchudelingService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schudeling")
public class SchudelingController {

    @Autowired
    private SchudelingService schudelingService;

    @GetMapping
    public ResponseEntity<List<Schudeling>> getAllSchudelings(){
        return ResponseEntity.ok(schudelingService.getAllSchudelings());
    }

    @GetMapping("{id}")
    public ResponseEntity<Schudeling> getSchudelingById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(schudelingService.getSchudelingById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Schudeling> createSchudeling(@RequestBody @Valid RecordSchudelingDto recordSchudelingDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(schudelingService.createSchudeling(recordSchudelingDto));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Schudeling> updateSchudeling(@PathVariable Long id , @RequestBody @Valid RecordSchudelingDto recordSchudelingDto){
        return ResponseEntity.status(HttpStatus.OK).body(schudelingService.updateSchudeling(id, recordSchudelingDto));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Object> deleteSchudeling(@PathVariable Long id){
        schudelingService.deleteSchudeling(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removed data.");
    }
}
