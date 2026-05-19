package org.example.oficina.dto;

public record VeiculoResponseDTO(

        Long idVeiculo,

        String placa,

        String modelo,

        Integer ano,

        Long clienteId,

        String nome,

        String telefone,

        String email

        //TODO: retornar informações do cliente
) {
}
