package org.example.oficina.dto;

public record OrdemServicoResponseDTO(
        Long idOrdemServico,

        String descricaoProblema,

        String status,

        Double valorServico,

        Long veiculoId,

        String placa,

        String modelo,

        Integer ano
) {
}
