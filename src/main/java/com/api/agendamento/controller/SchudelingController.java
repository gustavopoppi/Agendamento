package com.api.agendamento.controller;

import com.api.agendamento.dto.schudeling.ReadSchudelingDto;
import com.api.agendamento.dto.schudeling.RecordSchudelingDto;
import com.api.agendamento.service.SchudelingService;
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
    public ResponseEntity<List<ReadSchudelingDto>> getAllSchudelings(){
        return ResponseEntity.ok(schudelingService.getAllSchudelings());
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadSchudelingDto> getSchudelingById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(schudelingService.getSchudelingById(id));
    }

    @PostMapping
    public ResponseEntity<ReadSchudelingDto> createSchudeling(@RequestBody @Valid RecordSchudelingDto recordSchudelingDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(schudelingService.recordSchudeling(recordSchudelingDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ReadSchudelingDto> updateSchudeling(@PathVariable Long id , @RequestBody @Valid RecordSchudelingDto recordSchudelingDto){
        return ResponseEntity.status(HttpStatus.OK).body(schudelingService.updateSchudeling(id, recordSchudelingDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteSchudeling(@PathVariable Long id){
        schudelingService.deleteSchudeling(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removed data.");
    }
}
