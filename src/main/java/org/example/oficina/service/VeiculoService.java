package org.example.oficina.service;

import org.example.oficina.dto.VeiculoRequestDTO;
import org.example.oficina.dto.VeiculoResponseDTO;
import org.example.oficina.exception.RecursoNaoEncontradoException;
import org.example.oficina.model.Cliente;
import org.example.oficina.model.Veiculo;
import org.example.oficina.repository.ClienteRepository;
import org.example.oficina.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ClienteService clienteService;

    public List<VeiculoResponseDTO> listarTodos() {
        return veiculoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public VeiculoResponseDTO buscarPorId(Long idVeiculo) {
        Veiculo veiculo = veiculoRepository.findById(idVeiculo)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado"));

        return converterParaResponse(veiculo);
    }

    public VeiculoResponseDTO cadastrar(VeiculoRequestDTO dto) {
        Cliente cliente = clienteService.buscarEntidade(dto.clienteId());
        //TODO: adicionar a exception

        Veiculo veiculo = new Veiculo();

        veiculo.setPlaca(dto.placa());
        veiculo.setModelo(dto.modelo());
        veiculo.setAno(dto.ano());
        veiculo.setCliente(cliente);

        Veiculo salvo = veiculoRepository.save(veiculo);

        return converterParaResponse(salvo);
    }

    public VeiculoResponseDTO atualizar(Long idVeiculo, VeiculoRequestDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(idVeiculo)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado"));
        //TODO: adicionar a exception

        Cliente cliente = clienteService.buscarEntidade(dto.clienteId());
        //TODO: adicionar a exception

        veiculo.setPlaca(dto.placa());
        veiculo.setModelo(dto.modelo());
        veiculo.setAno(dto.ano());
        veiculo.setCliente(cliente);

        Veiculo atualizado = veiculoRepository.save(veiculo);

        return converterParaResponse(atualizado);
    }

    public void deletar(Long idVeiculo) {
        Veiculo veiculo = veiculoRepository.findById(idVeiculo)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado"));
        veiculoRepository.delete(veiculo);
        //TODO: adicionar a exception

        //TODO: chamar método de deletar do repository
    }

    public List<VeiculoResponseDTO> filtrarPorPlaca(String placa) {
        return veiculoRepository.findByPlacaContainingIgnoreCase(placa)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public List<VeiculoResponseDTO> listarPorCliente(Long idCliente) {
        return veiculoRepository.findByClienteIdCliente(idCliente)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public Veiculo buscarEntidade(Long idVeiculo){
        Veiculo veiculo = veiculoRepository.findById(idVeiculo)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veículo não encontrado"));
        return veiculo;
    }

    private VeiculoResponseDTO converterParaResponse(Veiculo veiculo) {
        return new VeiculoResponseDTO(
                //TODO: fazer os gets de "veiculo" conforme o que deve aparecer no response
                veiculo.getIdVeiculo(),
                veiculo.getPlaca(),
                veiculo.getModelo(),
                veiculo.getAno(),
                veiculo.getCliente().getIdCliente(),
                veiculo.getCliente().getNome(),
                veiculo.getCliente().getTelefone(),
                veiculo.getCliente().getEmail()
        );
    }
}
