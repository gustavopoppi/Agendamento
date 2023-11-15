package com.api.agendamento.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RecordCustomerDto(@NotNull String nome, @NotNull @Email String email, @NotNull String telefone) {
}