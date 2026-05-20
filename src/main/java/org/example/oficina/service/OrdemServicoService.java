package org.example.oficina.service;

import org.example.oficina.dto.*;
import org.example.oficina.exception.RecursoNaoEncontradoException;
import org.example.oficina.model.Cliente;
import org.example.oficina.model.OrdemServico;
import org.example.oficina.model.Veiculo;
import org.example.oficina.repository.OrdemServicoRepository;
import org.example.oficina.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoService {
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private VeiculoService veiculoService;

    public List<OrdemServicoResponseDTO> listarTodos() {
        return ordemServicoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public OrdemServicoResponseDTO buscarPorId(Long idOrdemServico) {
        OrdemServico ordemServico = ordemServicoRepository.findById(idOrdemServico)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ordem de serviço não encontrada"));

        return converterParaResponse(ordemServico);
    }

    public OrdemServicoResponseDTO cadastrar(OrdemServicoRequestDTO dto) {
        Veiculo veiculo = veiculoService.buscarEntidade(dto.veiculoId());

        OrdemServico ordemServico = new OrdemServico();

        ordemServico.setDescricaoProblema(dto.descricaoProblema());
        ordemServico.setStatus(dto.status());
        ordemServico.setValorServico(dto.valorServico());
        ordemServico.setVeiculo(veiculo);

        OrdemServico salvo = ordemServicoRepository.save(ordemServico);

        return converterParaResponse(salvo);
    }

    public OrdemServicoResponseDTO atualizar(Long idOrdemServico, OrdemServicoRequestDTO dto) {
        OrdemServico ordemServico = ordemServicoRepository.findById(idOrdemServico)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ordem de serviço não encontrada"));

        Veiculo veiculo = veiculoService.buscarEntidade(dto.veiculoId());

        ordemServico.setDescricaoProblema(dto.descricaoProblema());
        ordemServico.setStatus(dto.status());
        ordemServico.setValorServico(dto.valorServico());
        ordemServico.setVeiculo(veiculo);

        OrdemServico atualizado = ordemServicoRepository.save(ordemServico);

        return converterParaResponse(atualizado);
    }

    public void deletar(Long idOrdemServico) {
        OrdemServico ordemServico = ordemServicoRepository.findById(idOrdemServico)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ordem de serviço não encontrada"));
        ordemServicoRepository.delete(ordemServico);
    }




    private OrdemServicoResponseDTO converterParaResponse(OrdemServico ordemServico){
        return new OrdemServicoResponseDTO(
                ordemServico.getIdOrdemServico(),
                ordemServico.getDescricaoProblema(),
                ordemServico.getStatus(),
                ordemServico.getValorServico(),
                ordemServico.getVeiculo().getIdVeiculo(),
                ordemServico.getVeiculo().getPlaca(),
                ordemServico.getVeiculo().getModelo(),
                ordemServico.getVeiculo().getAno()
        );
    }
}
