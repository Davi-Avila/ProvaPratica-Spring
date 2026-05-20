package org.example.oficina.service;

import org.example.oficina.dto.ClienteRequestDTO;
import org.example.oficina.dto.ClienteResponseDTO;
import org.example.oficina.exception.RecursoNaoEncontradoException;
import org.example.oficina.model.Cliente;
import org.example.oficina.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteResponseDTO> listarTodos(){
        return clienteRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long idCliente){
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));

        return converterParaResponse(cliente);
    }

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto){
        Cliente cliente = new Cliente();

        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());

        Cliente salvo = clienteRepository.save(cliente);

        return converterParaResponse(salvo);
    }

    public ClienteResponseDTO atualizar(Long idCliente, ClienteRequestDTO dto){
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));

        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());

        Cliente atualizado = clienteRepository.save(cliente);

        return converterParaResponse(atualizado);
    }

    public void deletar(Long idCliente){
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }




    public Cliente buscarEntidade(Long idCliente){
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        return cliente;
    }


    public ClienteResponseDTO converterParaResponse(Cliente cliente){
        return new ClienteResponseDTO(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail()
        );
    }

}
