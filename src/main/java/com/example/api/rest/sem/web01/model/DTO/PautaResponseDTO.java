package com.example.api.rest.sem.web01.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PautaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
}
