package org.example.oficina.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VeiculoRequestDTO(

        //TODO: colocar validações de entrada de dados
        @NotBlank(message = "O número da placa é obrigatório!")
        String placa,

        @NotBlank(message = "O modelo do veículo é obrigatório!")
        String modelo,

        @NotNull(message = "O ano do veículo é obrigatório!")
        Integer ano,

        @NotNull(message = "O id do cliente é obrigatório")
        Long clienteId
) {
}
