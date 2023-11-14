package com.api.agendamento.controller;

import com.api.agendamento.service.ProfessionalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("professional")
public class ProfessinaoController {

    private final ProfessionalService professionalService;

    public ProfessinaoController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }
}