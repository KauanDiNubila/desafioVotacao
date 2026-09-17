package com.example.api.rest.sem.web01.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarPautaRequestDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;
}
