package com.api.agendamento.dto;

import com.api.agendamento.model.Customer;
import com.api.agendamento.model.Professional;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RecordSchudelingDto(@NotNull LocalDateTime data, @NotNull Customer customer, @NotNull Professional professional) {
}