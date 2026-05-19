package org.example.oficina.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVeiculo;

    private String placa;

    private String modelo;

    private Integer ano;

    //TODO: corrigir o relacionamento entre Veiculo e Cliente
    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @OneToMany(mappedBy = "veiculo")
    List<OrdemServico> ordemServicos = new ArrayList<>();

    public Veiculo(){}
}
