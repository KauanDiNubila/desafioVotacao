package com.example.api.rest.sem.web01.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SessaoResponseDTO {

    private Long id;
    private Long pautaId;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}
