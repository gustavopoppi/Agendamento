package com.api.agendamento.controller;

import com.api.agendamento.model.Schudeling;
import com.api.agendamento.service.SchudelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public ResponseEntity<Object> getSchudelingById(@PathVariable Long id){
        Optional<Schudeling> schudeling = schudelingService.getSchudelingById(id);
        return schudeling.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.ok().body("No data for this id"));
    }
}
