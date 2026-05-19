package org.example.oficina.dto;

public record ClienteResponseDTO (

        //TODO: colocar dados trazidos no response
        Long idCliente,

        String nome,

        String telefone,

        String email
){
}
