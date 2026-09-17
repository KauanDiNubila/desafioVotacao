package com.example.api.rest.sem.web01.controller;

import com.example.api.rest.sem.web01.model.DTO.CriarPautaRequestDTO;
import com.example.api.rest.sem.web01.model.DTO.PautaResponseDTO;
import com.example.api.rest.sem.web01.model.entity.Pauta;
import com.example.api.rest.sem.web01.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pautas")
@RequiredArgsConstructor
@Tag(name = "Pautas", description = "Criação e consulta de pautas de votação")
public class PautaController {

    private final PautaService pautaService;

    @PostMapping
    @Operation(summary = "Cria uma nova pauta")
    public ResponseEntity<PautaResponseDTO> criarPauta(@RequestBody @Valid CriarPautaRequestDTO dto) {

        Pauta pauta = new Pauta();
        pauta.setTitulo(dto.getTitulo());
        pauta.setDescricao(dto.getDescricao());

        Pauta salva = pautaService.criarPauta(pauta);

        PautaResponseDTO response = new PautaResponseDTO(salva.getId(), salva.getTitulo(), salva.getDescricao(), salva.getDataCriacao());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma pauta por id")
    public ResponseEntity<PautaResponseDTO> buscar(@PathVariable Long id) {

        Pauta pauta = pautaService.buscarPorId(id);

        PautaResponseDTO response = new PautaResponseDTO(pauta.getId(),
                pauta.getTitulo(),
                pauta.getDescricao(),
                pauta.getDataCriacao());

        return ResponseEntity.ok(response);
    }
}
