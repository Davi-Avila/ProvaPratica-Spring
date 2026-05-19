package org.example.oficina.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrdemServicoRequestDTO(
        @NotBlank(message = "A descrição do problema é obrigatória!")
        String descricaoProblema,

        @NotBlank(message = "O status do serviço é obrigatório!")
        String status,

        @NotNull(message = "O valor do serviço é obrigatório!")
        Double valorServico,

        @NotNull(message = "O id do veículo é obrigatório!")
        Long veiculoId
) {
}