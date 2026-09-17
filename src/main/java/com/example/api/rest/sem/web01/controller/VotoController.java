package com.example.api.rest.sem.web01.controller;

import com.example.api.rest.sem.web01.model.DTO.ResultadoResponseDTO;
import com.example.api.rest.sem.web01.model.DTO.VotoRequestDTO;
import com.example.api.rest.sem.web01.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pautas/{pautaId}/votos")
@RequiredArgsConstructor
@Tag(name = "Votos", description = "Registro de votos e apuração de resultado")
public class VotoController {

    private final VotoService votoService;

    @PostMapping
    @Operation(summary = "Registra o voto de um associado na sessão aberta da pauta")
    public ResponseEntity<Void> votar(
            @PathVariable Long pautaId,
            @RequestBody @Valid VotoRequestDTO dto) {

        votoService.votar(
                pautaId,
                dto.getAssociadoId(),
                dto.getVoto()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/resultado")
    @Operation(summary = "Apura o resultado da votação de uma pauta")
    public ResponseEntity<ResultadoResponseDTO> resultado(
            @PathVariable Long pautaId) {

        ResultadoResponseDTO resultado = votoService.obterResultado(pautaId);

        return ResponseEntity.ok(resultado);
    }
}
