package org.example.oficina.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.oficina.dto.ClienteRequestDTO;
import org.example.oficina.dto.ClienteResponseDTO;
import org.example.oficina.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Rotas para gerenciamento de clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @Operation(summary = "Lista todos os clientes")
    @GetMapping
    public List<ClienteResponseDTO> listar(){
        return service.listarTodos();
    }

    @Operation(summary = "Busca um cliente por id")
    @GetMapping("/{idCliente}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long idCliente){
        return service.buscarPorId(idCliente);
    }

    @Operation(summary = "Cadastra um novo cliente")
    @PostMapping
    public ClienteResponseDTO cadastrar(@Valid @RequestBody ClienteRequestDTO dto){
        return service.cadastrar(dto);
    }

    @Operation(summary = "Atualiza um cliente existente")
    @PutMapping("/{idCliente}")
    public ClienteResponseDTO atualizar(@PathVariable Long idCliente, @Valid @RequestBody ClienteRequestDTO dto){
        return service.atualizar(idCliente, dto);
    }

    @Operation(summary = "Remove um cliente")
    @DeleteMapping("/{idCliente}")
    public void deletar(@PathVariable Long idCliente){
        service.deletar(idCliente);
    }
}
