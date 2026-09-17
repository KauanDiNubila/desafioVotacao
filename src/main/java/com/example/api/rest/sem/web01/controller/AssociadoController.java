package com.example.api.rest.sem.web01.controller;

import com.example.api.rest.sem.web01.model.DTO.AssociadoRequestDTO;
import com.example.api.rest.sem.web01.model.entity.Associado;
import com.example.api.rest.sem.web01.service.AssociadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associados")
@RequiredArgsConstructor
@Tag(name = "Associados", description = "Cadastro de associados aptos a votar")
public class AssociadoController {

    private final AssociadoService associadoService;

    @PostMapping
    @Operation(summary = "Cadastra um novo associado")
    public ResponseEntity<Associado> criar(@RequestBody @Valid AssociadoRequestDTO dto) {

        Associado associadoCriado = associadoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(associadoCriado);
    }

    @GetMapping
    @Operation(summary = "Lista todos os associados")
    public ResponseEntity<List<Associado>> listar() {
        return ResponseEntity.ok(associadoService.listar());
    }
}
