package org.example.oficina.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.oficina.dto.OrdemServicoRequestDTO;
import org.example.oficina.dto.OrdemServicoResponseDTO;
import org.example.oficina.dto.VeiculoRequestDTO;
import org.example.oficina.dto.VeiculoResponseDTO;
import org.example.oficina.service.OrdemServicoService;
import org.example.oficina.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ordem de Serviços", description = "Rotas para gerenciamento de ordens de serviço")
@RestController
@RequestMapping("/ordemServicos")
public class OrdemServicoController {
    @Autowired
    private OrdemServicoService service;

    @Operation(summary = "Lista todas as ordens de serviço")
    @GetMapping
    public List<OrdemServicoResponseDTO> listar() {
        return service.listarTodos();
    }

    @Operation(summary = "Busca uma ordem de serviço por id")
    @GetMapping("/{idOrdemServico}")
    public OrdemServicoResponseDTO buscarPorId(@PathVariable Long idOrdemServico) {
        return service.buscarPorId(idOrdemServico);
    }

    @Operation(summary = "Cadastra uma nova ordem de serviço")
    @PostMapping
    public OrdemServicoResponseDTO cadastrar(@Valid @RequestBody OrdemServicoRequestDTO dto) {
        //TODO: usar a validação de dados
        return service.cadastrar(dto);
    }

    @Operation(summary = "Atualiza uma ordem de serviço existente")
    @PutMapping("/{idOrdemServico}")
    public OrdemServicoResponseDTO atualizar(@PathVariable Long idOrdemServico, @Valid @RequestBody OrdemServicoRequestDTO dto) {
        return service.atualizar(idOrdemServico, dto);
    }

    @Operation(summary = "Remove uma ordem de serviço")
    @DeleteMapping("/{idOrdemServico}")
    public void deletar(@PathVariable Long idOrdemServico) {
        service.deletar(idOrdemServico);
    }

}
