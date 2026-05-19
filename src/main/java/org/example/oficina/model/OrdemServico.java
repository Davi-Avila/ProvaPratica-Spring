package org.example.oficina.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdemServico;

    private String descricaoProblema;

    private String status;

    private Double valorServico;

    //TODO: transformar em entidade e colocar relacionamento
    @ManyToOne
    @JoinColumn(name = "veiculoId")
    private Veiculo veiculo;

    public OrdemServico(){}
}
