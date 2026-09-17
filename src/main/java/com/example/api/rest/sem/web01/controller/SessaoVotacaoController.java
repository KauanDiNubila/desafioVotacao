package com.example.api.rest.sem.web01.controller;

import com.example.api.rest.sem.web01.model.DTO.SessaoRequestDTO;
import com.example.api.rest.sem.web01.model.DTO.SessaoResponseDTO;
import com.example.api.rest.sem.web01.model.entity.SessaoVotacao;
import com.example.api.rest.sem.web01.service.SessaoVotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pautas/{pautaId}/sessoes")
@RequiredArgsConstructor
@Tag(name = "Sessões de votação", description = "Abertura de sessões de votação para uma pauta")
public class SessaoVotacaoController {

    private final SessaoVotacaoService sessaoVotacaoService;

    @PostMapping
    @Operation(summary = "Abre uma sessão de votação para a pauta (duração padrão de 1 minuto)")
    public ResponseEntity<SessaoResponseDTO> abrirSessao(@PathVariable Long pautaId,
                                                          @RequestBody(required = false) SessaoRequestDTO request) {

        Integer duracao = (request != null) ? request.getDuracaoMinutos() : null;

        SessaoVotacao sessao = sessaoVotacaoService.abrirSessao(pautaId, duracao);

        SessaoResponseDTO response = new SessaoResponseDTO(
                sessao.getId(),
                sessao.getPauta().getId(),
                sessao.getDataInicio(),
                sessao.getDataFim()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
