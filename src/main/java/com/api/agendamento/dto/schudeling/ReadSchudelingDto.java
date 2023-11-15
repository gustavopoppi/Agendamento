package com.api.agendamento.dto.schudeling;

import com.api.agendamento.model.Customer;
import com.api.agendamento.model.Professional;

import java.time.LocalDateTime;

public record ReadSchudelingDto(Long id, LocalDateTime data, Customer customer, Professional professional) {
}
