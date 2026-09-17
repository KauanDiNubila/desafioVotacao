package com.example.api.rest.sem.web01.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultadoResponseDTO {

    private Long pautaId;
    private long votosSim;
    private long votosNao;
    private String resultado;
}
