package org.example.oficina.repository;

import org.example.oficina.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByPlacaContainingIgnoreCase(String placa);
    List<Veiculo> findByClienteIdCliente(Long idCliente);

    // TODO: criar método para filtrar veículos pela placa.

    //TODO: criar método para listar veículos de um determinado cliente.

}
