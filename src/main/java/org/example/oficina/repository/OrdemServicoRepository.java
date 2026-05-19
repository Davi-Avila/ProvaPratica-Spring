package org.example.oficina.repository;

import org.example.oficina.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    //TODO: criar método para listar ordens de serviço de um determinado veículo.
}
