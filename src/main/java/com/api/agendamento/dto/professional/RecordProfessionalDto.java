package com.api.agendamento.dto.professional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RecordProfessionalDto(@NotNull String nome, @NotNull @Email String email, @NotNull String telefone) {
}